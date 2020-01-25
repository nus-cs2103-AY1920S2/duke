package dukebot;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> taskList;
    public Storage storage;

    public TaskList(String storePath) {
        this.storage = new Storage(storePath);
        taskList = this.storage.loadFromFile();
    }


}