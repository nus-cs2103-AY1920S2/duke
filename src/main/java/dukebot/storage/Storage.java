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
    private final String USER_DATA_DIRECTORY = "./user-data";
    private final String TASK_LIST_FILEPATH = "./user-data/task-list.data";
    private final String ALIAS_FILEPATH = "./user-data/alias.data";
    private final String SMALL_TALK_FILEPATH = "./user-data/small-talk.data";
    private final String CONFIG_FILEPATH = "./user-data/config.data";

    /**
     * Generates the Storage.
     *
     * @param storagePath  Path to save file.
     */
    public Storage(String storagePath) {
        this.storagePath = storagePath;
    }

    private void mkDataDir() {
        File directory = new File(USER_DATA_DIRECTORY);
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
            FileOutputStream writeData = new FileOutputStream(new File(this.TASK_LIST_FILEPATH));
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
        File file = new File(this.TASK_LIST_FILEPATH);
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