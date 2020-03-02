package storage;

import tasks.Task;
import tasks.TaskList;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Storage components which handles loading and saving of file.
 */
public class Storage {

    private String filePath;

    /**
     * Constructor of a storage component.
     * @param filePath Path of file we interact with.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns a list of tasks.
     * @return TaskList which is an abstraction of our task list.
     */
    //Reused from https://mkyong.com/java/how-to-read-and-write-java-object-to-a-file/
    // and https://mkyong.com/java/how-to-create-directory-in-java/
    // with minor modifications
    public TaskList load() {
        TaskList tasks = new TaskList(new ArrayList<Task>(), true);
        try {
            File file = new File(this.filePath);
            if (!file.exists()) {
                File directory = new File(file.getParent());
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                assert directory != null : "directory should be created";
                file.createNewFile();
                assert file != null : "new file should be created";
            } else {
                FileInputStream fi = new FileInputStream(file);
                ObjectInputStream oi = new ObjectInputStream(fi);
                tasks = new TaskList((List<Task>) oi.readObject(), true);
                oi.close();
                fi.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tasks;
    }

    /**
     * Saves our tasks in a file associated with this storage object.
     * @param tasks List of tasks to be saved.
     */
    public void save(TaskList tasks) {
        assert new File(this.filePath) != null : "file you are saving to should exist.";
        try {
            FileOutputStream fo = new FileOutputStream(new File(this.filePath));
            ObjectOutputStream oo = new ObjectOutputStream(fo);
            oo.writeObject(tasks.getTaskList());
            oo.close();
            fo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
