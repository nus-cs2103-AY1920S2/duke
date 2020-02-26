import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents an object that stores the save file of the user's task list,
 * and handles the saving and update of the task list.
 */
public class Storage {
    private Path filePath;
    private File file;

    /**
     * Constructor for a Storage object that takes in a String
     * representing the name of the task list save file.
     * @param fileName String of the task list save file name.
     */
    public Storage(String fileName) {
        // Load the file
        String currDir = System.getProperty("user.dir");
        this.filePath =  Paths.get(currDir, "list.txt");
        this.file = new File(filePath.toString());
        try {
            // create the file if it does not exist
            if (!file.exists()) {
                this.file.createNewFile();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

    public Path getFilePath() {
        return this.filePath;
    }

    public File getFile() {
        return this.file;
    }

    /**
     * Returns an ArrayList of String from reading the lines
     * from the user's task list file of the format .txt
     * @return ArrayList of String representing the user's task list,
     *     where each element corresponds to one task.
     */
    public ArrayList<String> loadFromFilePath() {
        List<String> lines = new ArrayList<>();
        try {
            Scanner sc = new Scanner(this.file);
            while (sc.hasNext()) {
                lines.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Task list save file not found!");
        }
        return new ArrayList<>(lines);
    }

    /**
     * Saves and updates the user's .txt task list.
     * @param taskList The ArrayList of Tasks that represents the updated list of tasks
     *                 to be saved to the .txt file.
     */
    public void saveFile(ArrayList<Task> taskList) {
        try {
            File file = new File(this.filePath.toString());
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Task t : taskList) {
                String taskString = t.toString();
                bw.write(taskString);
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving to file! :-(");
        }

    }


}
