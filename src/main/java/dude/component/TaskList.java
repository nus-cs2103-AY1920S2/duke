package dude.component;

import dude.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>(100);
    }

    public List<Task> getAllTasks() {
        return taskList;
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public Task getTask(int index) {
        return this.taskList.get(index - 1);
    }

    public Task removeTask(int index) {
        return this.taskList.remove(index - 1);
    }

    public String[] showAllTasks() {
        String[] result = new String[taskCount()];
        for (int i = 1; i <= taskCount(); i++) {
            result[i-1] = String.format("%d.%s", i, getTask(i));
        }
        return result;
    }

    public int taskCount() {
        return this.taskList.size();
    }
}
