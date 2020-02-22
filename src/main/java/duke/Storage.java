package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the file object to store the address book data
 */
public class Storage {

    public String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

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
            }
        }
        return tasks;
    }

    public static void fillFileWithTasks(ArrayList<Task> tasks) throws IOException {
        assert tasks.size() > 1 : "Task list provided is empty";
        FileWriter fw = new FileWriter("data/data.txt");
        String accumulatedTasks = "";
        for (int i = 0; i < tasks.size(); i++) {
            accumulatedTasks = accumulatedTasks + tasks.get(i).toFile() + "\n";
        }
        fw.write(accumulatedTasks);
        fw.close();
    }

    public static boolean getBooleanFromString(String s) {
        if (s.equals("0")) {
            return false;
        } else {
            return true;
        }
    }
}
