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
    private String storageDirectory;
    private final String TASK_LIST_FILEPATH = "/task-list.data";
    private final String ALIAS_FILEPATH = "/alias.data";
    private final String SMALL_TALK_FILEPATH = "/small-talk.data";
    private final String CONFIG_FILEPATH = "/config.data";

    /**
     * Generates the Storage with default directory.
     */
    public Storage() {
        this.storageDirectory = "./user-data";
    }

    /**
     * Generates the Storage.
     *
     * @param storageDirectory  Path to save file.
     */
    public Storage(String storageDirectory) {
        this.storageDirectory = storageDirectory;
    }

    private void mkDataDir() {
        File directory = new File(storageDirectory);
        if (! directory.exists()){
            directory.mkdir();
        }
    }

    /**
     * Saves data to drive.
     *
     * @param tasks The TaskList to save.
     * @throws DukeException  If save fails for the first time.
     */
    public void saveTaskList(TaskList tasks) throws DukeException {
        ArrayList<Task> taskList = tasks.getTaskList();
        try {
            mkDataDir();
            File file = new File(storageDirectory + TASK_LIST_FILEPATH);
            FileOutputStream writeData = new FileOutputStream(file);
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
            Task[] taskArr = taskList.toArray(new Task[0]);
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

    /**
     * Loads data from drive.
     *
     * @return The saved TaskList.
     * @throws DukeException  If no data is found.
     */
    public ArrayList<Task> loadTaskArrayList() throws DukeException {
        File file = new File(storageDirectory + TASK_LIST_FILEPATH);
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