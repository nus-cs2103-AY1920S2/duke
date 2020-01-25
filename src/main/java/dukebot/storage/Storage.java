package dukebot.storage;

import dukebot.exception.DukeException;
import dukebot.tasklist.Task;
import dukebot.tasklist.TaskList;
import dukebot.ui.LineName;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class Storage {
    private boolean saveAlreadyFailed = false;
    private String storagePath;

    public Storage(String storagePath) {
        this.storagePath = storagePath;
    }

    public void saveToFile(TaskList tasks) throws DukeException {
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
            if (!saveAlreadyFailed) {
                saveAlreadyFailed = true;
                throw new DukeException(LineName.SAVE_FAIL);
            }
        }
    }

    public ArrayList<Task> loadFromFile() throws DukeException{
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
        throw new DukeException(LineName.LOAD_FAIL);
    }
}