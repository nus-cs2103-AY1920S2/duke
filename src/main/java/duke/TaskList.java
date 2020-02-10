package duke;

import java.util.ArrayList;
import java.util.Collections;

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

    /**
     * Retrieves a list of tasks that matches with the keyword entered by user.
     *
     * @param keyword The keyword for the tasks to be matched against.
     * @return Returns the list of tasks with found matches.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getDescription().contains(keyword)) {
                result.add(t);
            }
        }
        return result;
    }

    /**
     * Sorts the task list in alphabetically order.
     *
     * @return Sorted task list.
     */
    public ArrayList<Task> sort() {
        Collections.sort(tasks);
        return tasks;
    }
}
