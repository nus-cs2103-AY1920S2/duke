package com.duke.bot;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the data structure that stores the tasks for Duke Bot.
 */
public class TaskList {
    List<Task> tasks;

    private TaskList() {
        this(0);
    }

    private TaskList(int n) {
        tasks = new ArrayList<>(n);
    }

    /**
     * Factory method for creating a TaskList object.
     *
     * @return A TaskList object.
     */
    public static TaskList createTaskList() {
        return new TaskList();
    }

    /**
     * Returns the number of tasks stored in the Task List.
     *
     * @return The number of tasks stored in the Task List.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Adds a task into the Task List.
     *
     * @param task The task being added into the Task List.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the task that is located at a specific index in the Task List.
     *
     * @param index the index of the targeted task being retrieved from the Task List.
     * @return The task located at the specified index in the Task List.
     * @throws IndexOutOfBoundsException If the index is smaller than 0 or larger than
     *     or equal to the number of tasks in the Task List.
     */
    public Task getTask(int index) throws IndexOutOfBoundsException {
        return tasks.get(index);
    }

    /**
     * Deletes a task located at the a certain index number of the Task List.
     *
     * @param delIdx The index number of the task being deleted.
     * @throws IndexOutOfBoundsException If the index in out of range of the Task List.
     */
    public void deleteTask(int delIdx) throws IndexOutOfBoundsException {
        if (delIdx >= tasks.size() || delIdx <= -1) {
            throw new IndexOutOfBoundsException("Oops! Target object is out of bounds!\n"
            + String.format("Task size: %d\n", tasks.size())
            + String.format("final Index size: %d\n", delIdx));
        }
        tasks.remove(delIdx);
    }

    /**
     * Returns a text containing all the tasks in the Task List in order.
     *
     * @return A string containing all the tasks in the Task List in order.
     */
    public String printList() {
        String output = "";
        output += ("Here is your list of tasks:\n");
        for (int i = 0; i < tasks.size(); ++i) {
            Task task = tasks.get(i);
            output = output.concat(String.format("%d. %s\n", i + 1, task.toString()));
        }
        return output;
    }

}
