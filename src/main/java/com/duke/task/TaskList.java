package com.duke.task;

import java.util.ArrayList;

/**
 * Represents a handler that stores manages list that
 * contains all the task information.
 */
public class TaskList {
    /**
     * A list that stores all the task information.
     */
    public ArrayList<Task> tasks;

    /**
     * Instantiates a <code>TaskList</code> object with empty task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Instantiates a <code>TaskList</code> object with task list represnted by
     * the <code>ArrayList</code> object.
     *
     * @param t the list of tasks.
     */
    public TaskList(ArrayList<Task> t) {
        tasks = t;
    }

    /**
     * Marks a Task with the given index as done.
     *
     * @param tindex The index of the Task in the list to be marked as done.
     * @return The Task to be marked as done.
     */
    public Task markTask(int tindex) {
        Task out = tasks.get(tindex);
        out.isDone = true;
        return out;
    }

    /**
     * Deletes a Task with the given index as done.
     *
     * @param tindex The index of the Task in the list to be deleted.
     * @return The Task to be deleted.
     */
    public Task deleteTask(int tindex) {
        return tasks.remove(tindex);
    }

    /**
     * Adds a Task to the list.
     *
     * @return The Task to be added.
     */
    public Task addTask(Task t) {
        tasks.add(t);
        return t;
    }

    /**
     * Finds the tasks that in the task list whose description contains
     * the keyword specified.
     *
     * @param keyword The keyword to be used to search through the task list.
     * @return A list of tasks that contains the given keyword in their description.
     */
    public ArrayList<Task> findTask(String keyword) {
        ArrayList<Task> out = new ArrayList<>();
        for (Task t : tasks) {
            if (t.description.contains(keyword)) {
                out.add(t);
            }
        }
        return out;
    }
}
