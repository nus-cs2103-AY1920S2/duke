package duke.io;

import duke.task.TaskList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * A serializer for saving and loading of duke.Duke data from disk.
 */
public class Serializer {
    public static final String FILENAME = "tasks.data";

    /**
     * Serializes the input TaskList to file.
     * Writes the entire TaskList to Serializer.FILENAME using a File/Object Output Stream.
     *
     * @param taskList The TaskList to serialize to file.
     */
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

    /**
     * De-serializes the input TaskList to file.
     * Reads the entire TaskList from Serializer.FILENAME using a File/Object Input Stream.
     * If the file is not found, corrupted, or unable to be read, this function will return null.
     *
     * @return A fully populated TaskList that had previously been serialized
     */
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
            // duke.task.TaskList cannot be loaded
            return null;
        } catch (ClassNotFoundException e) {
            // File is corrupted
            File file = new File(FILENAME);

            // Try deleting the file and try again.
            // TODO: This operation is probably not very safe. Think of something else.
            if (file.delete()) {
                return deserialize();
            }
            return null;
        }

        return taskList;
    }
}
