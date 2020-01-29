package com.duke.bot;

import java.util.ArrayList;
import java.util.List;
import com.duke.bot.task.Task;

public class TaskList {
    private final List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public TaskList add(Task task) {
        List<Task> newTasks = new ArrayList<>(tasks);
        newTasks.add(task);
        return new TaskList(newTasks);
    }

    public TaskList remove(int index) {
        List<Task> newTasks = new ArrayList<>(tasks);
        newTasks.remove(index);
        return new TaskList(newTasks);
    }

    public TaskList set(int index, Task task) {
        List<Task> newTasks = new ArrayList<>(tasks);
        newTasks.set(index, task);
        return new TaskList(newTasks);
    }

    public List<Task> getUnderlyingList() {
        return tasks;
    }
}
