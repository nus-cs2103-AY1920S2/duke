package ip;

import java.io.*;

public class Storage {
    private final String FILENAME = "yourfile.txt";
    public void writeToFile(TaskList tasks) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(FILENAME));
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
            objectOutputStream.writeObject(tasks);
        } catch (IOException e) {}
    }
    public TaskList readFromFile() {
        try (FileInputStream fileInputStream = new FileInputStream(new File(FILENAME));
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
            TaskList tasks = (TaskList) objectInputStream.readObject();
            return tasks;
        } catch (IOException | ClassNotFoundException e) {
            return new TaskList();
        }
    }
}
