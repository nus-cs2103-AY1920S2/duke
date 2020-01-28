package duke.pack;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public void deleteTask(int taskNum) {
        list.remove(taskNum - 1);
    }


}
