import java.io.*;

/**
 * A serializer for saving and loading of Duke data from disk.
 */
public class Serializer {
    public static final String FILENAME = "tasks.data";

    public static void serialize(TaskList taskList) {
        FileOutputStream fos;
        ObjectOutputStream oos;

        try {
            fos = new FileOutputStream(FILENAME);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(taskList);
            oos.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static TaskList deserialize() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        TaskList taskList = null;
        try {
            fis = new FileInputStream(FILENAME);
            ois = new ObjectInputStream(fis);
            taskList = (TaskList) ois.readObject();
            ois.close();
        } catch (IOException e) {
            // TaskList cannot be loaded
            return null;
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            return null;
        }

        return taskList;
    }
}
