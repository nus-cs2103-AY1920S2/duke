package duke.main;

import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Storage {
    // private static final String storagePath = "data/duke.txt";
    private final String storagePath;

    public Storage(String filePath) {
        this.storagePath = filePath;
    }

    public void appendToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(storagePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Writes to the file whose address is specified by storagePath.
     * @param textToAdd The text that is added to the file.
     */
    public void writeToFile(String textToAdd) {
        try {
            FileWriter fw = new FileWriter(storagePath);
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Takes the tasks in taskList and write all of them into the file whose address is specified by storagePath.
     * @param taskList The List of Tasks to be written.
     */
    public void writeTasks(TaskList taskList) {
        List<Task> tasks = taskList.getTasks();
        StringBuilder sb = new StringBuilder();
        for (Task task: tasks) {
            sb.append(task.toStorageString());
        }
        writeToFile(sb.toString());
    }

    /**
     * Reads the file that is pointed by storagePath and write store them as a List<List<String>>.
     * The List<String> can be a list of a single command or a command with the arguments.
     * @return The List<List<String>> version of the file being read.
     * @throws FileNotFoundException If file is not found.
     */
    public List<List<String>> loadTasksFromSaveFile() throws IOException {
        List<List<String>> tasks = new ArrayList<>();
        File f = new File(storagePath);
        if (!f.exists()) {
            f.getParentFile().mkdirs();
            f.createNewFile();

            // early termination because we know that the file never existed
            return tasks;
        }

        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            String line = sc.nextLine();
            // It's formatted with the pipe
            String[] separated = line.split(" \\| ");

            // Turn the String array into an array list
            List<String> al = Arrays.asList(separated);
            tasks.add(al);
        }
        return tasks;
    }
}
