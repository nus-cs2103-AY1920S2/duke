package dukebot;

import dukebot.tasklist.Task;
import dukebot.tasklist.TaskList;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Storage {
    private boolean failedSave;
    private String storagePath;

    public Storage(String storagePath) {
        this.storagePath = storagePath;
    }

    public void saveToFile(TaskList tasks) {
        ArrayList<Task> taskList = tasks.getTaskList();
        try {
            FileOutputStream writeData = new FileOutputStream(new File(this.storagePath));
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
            Task[] taskArr = taskList.toArray(new Task[taskList.size()]);
            writeStream.writeObject(taskArr);
            writeStream.flush();
            writeStream.close();

        } catch (IOException e) {
            // e.printStackTrace();
        }
    }

    public ArrayList<Task> loadFromFile() {
        File file = new File(this.storagePath);
        if (file.isFile()) {
            try {
                FileInputStream readData = new FileInputStream(file);
                ObjectInputStream readStream = new ObjectInputStream(readData);
                Object obj = readStream.readObject();
                if (obj instanceof Task[]) {
                    return new ArrayList<>(Arrays.asList((Task[]) obj));
                }
            } catch (IOException | ClassNotFoundException e) {
                // e.printStackTrace();
            }
        }
        System.out.println("fail load");
        return new ArrayList<>();
    }
}