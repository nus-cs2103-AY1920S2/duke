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
    public String list() {
        String msg = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            msg += i + 1 + "." + tasks.get(i) + "\n";
        }
        return msg;
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
    public String find(String keyword) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getDescription().contains(keyword)) {
                result.add(t);
            }
        }
        String msg;
        if (result.size() == 0) {
            msg = "No match found.";
        } else {
            msg = "Here are the matching tasks in your list:\n";
            for (int i = 0; i < result.size(); i++) {
                msg += i + 1 + "." + result.get(i) + "\n";
            }
        }
        return msg;
    }
}
