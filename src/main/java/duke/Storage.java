package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the data that is stored in the text file specified by it's filePath.
     * @return An ArrayList of tasks that is potentially empty.
     * @throws FileNotFoundException is thrown if file is unable to be opened.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File f = new File(filePath);
            Scanner s = s = new Scanner(f);

            while (s.hasNext()) {
                tasks.add(databaseStringToTask(s.nextLine()));
            }
        } catch (FileNotFoundException e) {
            createSaveFile();
            System.out.println("file not found, creating new empty file");
        }

        return tasks;
    }

    private void createSaveFile() {
        File tempFile = new File(filePath);
        tempFile.getParentFile().mkdir();
        try {
            tempFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error creating new save file");
        }
    }

    private static Task databaseStringToTask(String s) {
        String[] split = s.split("\\|");
        Task newTask = new Todo("error in decoding database string");

        switch (split[0]) {
        case "T":
            newTask = new Todo(split[2]);
            break;
        case "D":
            newTask = new Deadline(split[2], LocalDate.parse(split[3]));
            break;
        case "E":
            newTask = new Event(split[2], split[3]);
            break;
        default:
            System.err.println("default case hit in decoding database string to task");
        }

        // set completion status
        if (split[1].equals("1")) {
            newTask.markAsDone();
        }
        return newTask;
    }

    /**
     * Saves the state of the given TaskList into the local text file.
     * @param tasks The TaskList to be saved.
     * @throws IOException when call to underlying FileWrite has failed.
     */
    public void save(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        for (Task t : tasks.getTasks()) {
            fw.write(t.toDatabaseString());
        }
        fw.close();
    }
}
