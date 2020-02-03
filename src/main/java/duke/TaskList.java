package duke;

import java.util.ArrayList;

/**
 * Manages the tasks of the user.
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    /**
     * TaskList constructor.
     *
     * @param tasks ArrayList of task.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * List out all the tasks currently in the task list.
     */
    public void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + "." + tasks.get(i));
        }
    }

    /**
     * Returns a task specified by the user.
     *
     * @param taskNumber The task number.
     */
    public Task get(int taskNumber) {
        return tasks.get(taskNumber);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the number of tasks currently in the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Remove a task from the task list.
     *
     * @param taskNumber Specifies which task to remove.
     * @return Returns the task removed.
     */
    public Task remove(int taskNumber) {
        return tasks.remove(taskNumber);
    }
}
