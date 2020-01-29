import java.io.*;
import java.util.List;

/**
 * A serializer for saving and loading of Duke data from disk.
 */
public class Serializer {
    public static final String FILENAME = "tasks.data";

    public static void serialize(List<Task> tasks) {
        FileOutputStream fos;
        ObjectOutputStream oos;

        try {
            fos = new FileOutputStream(FILENAME);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(tasks);
            oos.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static List<Task> deserialize() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        List<Task> tasks = null;
        try
        {
            fis = new FileInputStream(FILENAME);
            ois = new ObjectInputStream(fis);
            tasks = (List<Task>) ois.readObject();
            ois.close();
        } catch (IOException e) {
            // File does not exist
            return null;
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }

        return tasks;
    }
}
