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

    /**
     * Adds a task to the list.
     * This method is immutable; thus, it returns a newly created list instead of
     * mutating the current instance.
     * 
     * @param task Task to be added to the list
     * @return A newly created list with the added task
     */
    public TaskList add(Task task) {
        List<Task> newTasks = new ArrayList<>(tasks);
        newTasks.add(task);
        return new TaskList(newTasks);
    }

    /**
     * Removes a task from the list.
     * This method is immutable; thus, it returns a newly created list instead of
     * mutating the current instance.
     * 
     * @param index Index of the task to be deleted
     * @return A newly created list without the deleted task
     */
    public TaskList remove(int index) {
        List<Task> newTasks = new ArrayList<>(tasks);
        newTasks.remove(index);
        return new TaskList(newTasks);
    }

    /**
     * Replaces the task at a specific index with another task.
     * This method is immutable; thus, it returns a newly created list instead of
     * mutating the current instance.
     * 
     * @param index Index of the task to be replaced
     * @param task A task that will occupy the specified index at the end of the operation
     * @return A newly created list with the task replaced
     */
    public TaskList set(int index, Task task) {
        List<Task> newTasks = new ArrayList<>(tasks);
        newTasks.set(index, task);
        return new TaskList(newTasks);
    }

    public List<Task> getUnderlyingList() {
        return tasks;
    }
}
