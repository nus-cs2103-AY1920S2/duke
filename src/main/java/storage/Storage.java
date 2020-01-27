package storage;

import tasks.Task;
import tasks.TaskList;

import java.io.*;
import java.util.List;

public class Storage {
    // deals with loading tasks from the file and saving tasks in the file
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList load() {
        TaskList tasks = new TaskList();
        try {
            File file = new File(this.filePath);
            if (!file.exists()) {
                File directory = new File(file.getParent());
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                file.createNewFile();
            } else {
                FileInputStream fi = new FileInputStream(file);
                ObjectInputStream oi = new ObjectInputStream(fi);
                tasks.importTasks((List<Task>) oi.readObject());
                oi.close();
                fi.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public void save(TaskList tasks) {
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
