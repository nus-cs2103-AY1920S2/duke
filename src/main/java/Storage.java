import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }


    /**
     * Retrieves tasks from Hard Disk.
     * @param path Path to file in Hard Disk.
     * @return Arraylist containing all the tasks.
     * @throws FileNotFoundException if file pointed to by path is not found.
     */
    public ArrayList<Task> getTasksFromFile(String path) throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>(100);
        File file = new File(path);
        Scanner in = new Scanner(file);
        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] taskComponents = line.split(" \\| ");
            Task t;
            switch (taskComponents[0]) {
            case "T":
                t = new ToDo(taskComponents[2], stringToBoolean(taskComponents[1]));
                tasks.add(t);
                break;
            case "D":
                t = new Deadline(taskComponents[2], taskComponents[3], stringToBoolean(taskComponents[1]));
                tasks.add(t);
                break;
            case "E":
                t = new Event(taskComponents[2], taskComponents[3], stringToBoolean(taskComponents[1]));
                tasks.add(t);
                break;
            default:
                break;
            }
        }
        return tasks;
    }

    /**
     * Adds tasks to file.
     *
     * @param tasks Tasks to be added to file.
     * @throws IOException if there are input/output problems.
     */
    public void addTasksToFile(ArrayList<Task> tasks) throws IOException {
        //File file = new File(filePath);
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        FileWriter fw = new FileWriter(filePath);
        int size = tasks.size();
        String str = "";
        Task t;
        for (int i = 0; i < size; ++i) {
            t = tasks.get(i);
            str = str + t.addToFile() + "\n";
        }
        fw.write(str);
        fw.close();
    }

    /**
     * Converts string to boolean.
     * @param str String to be converted.
     * @return Whether task is completed.
     */
    public static boolean stringToBoolean(String str) {
        if (str.equals("0")) {
            return false;
        } else {
            return true;
        }
    }
}
