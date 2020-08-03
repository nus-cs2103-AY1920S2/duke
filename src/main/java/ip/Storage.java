package ip;

import ip.task.TaskList;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

public class Storage {
    private static final String FILENAME = "yourfile.txt";

    /**
     * Write all the user's tasks to local storage
     * @param tasks the user's tasks
     */
    public void writeToFile(TaskList tasks) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(FILENAME));
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(tasks);
        } catch (IOException e) {}
    }

    /**
     * Reads the user's tasks from local storage.
     * @return the user's tasks
     */
    public TaskList readFromFile() {
        try (FileInputStream fileInputStream = new FileInputStream(new File(FILENAME));
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            TaskList tasks = (TaskList) objectInputStream.readObject();
            return tasks;
        } catch (IOException | ClassNotFoundException e) {
            return new TaskList();
        }
    }
}
