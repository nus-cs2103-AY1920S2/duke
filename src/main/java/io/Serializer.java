package io;

import task.TaskList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
            // task.TaskList cannot be loaded
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
