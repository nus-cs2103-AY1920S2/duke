package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the file object to store the address book data.
 */
public class Storage {

    public static final String FILE_PATH = "data/data.txt";
    public String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Gets task from a stored file at given file path.
     *
     * @param path the relative path of the storage file
     * @return an ArrayList of tasks
     * @throws FileNotFoundException an exception if the storage file is not found.
     */
    public static ArrayList<Task> getPreviousTasks(String path) throws FileNotFoundException {
        File file = new File(path);
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner input = new Scanner(file);
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] data = line.split(" \\| ");
            Task task;
            boolean isValidBooleanLiteral = data[1].equals("0") || data[1].equals("1");
            assert isValidBooleanLiteral : "Invalid Boolean Literal";
            switch (data[0]) {
                case "T":
                    task = new ToDo(data[2], getBooleanFromString(data[1]));
                    tasks.add(task);
                    break;
                case "D":
                    task = new Deadline(data[2], data[3], getBooleanFromString(data[1]));
                    tasks.add(task);
                    break;
                case "E":
                    task = new Event(data[2], data[3], getBooleanFromString(data[1]));
                    tasks.add(task);
                    break;
                default:
                    System.out.println("Unknown task stored in the list");
            }
        }
        return tasks;
    }

    /**
     * Stores the given tasks into the storage file.
     * @param tasks an array list containing tasks.
     * @throws IOException when file is not found.
     */
    public static void fillFileWithTasks(ArrayList<Task> tasks) throws IOException {
        assert tasks.size() > 1 : "Task list provided is empty";
        FileWriter fw = new FileWriter(FILE_PATH);
        String accumulatedTasks = "";
        for (int i = 0; i < tasks.size(); i++) {
            accumulatedTasks = accumulatedTasks + tasks.get(i).toFile() + "\n";
        }
        fw.write(accumulatedTasks);
        fw.close();
    }

    /**
     * Generates a boolean value from a string representation of 0 or 1.
     * @param s the string input
     * @return true or false.
     */
    public static boolean getBooleanFromString(String s) {
        if (s.equals("0")) {
            return false;
        } else {
            return true;
        }
    }
}
