package duke;

import java.util.ArrayList;

/**
 * TaskList represents a list of tasks and stores tasks. Uses ArrayList to store tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object. Initialized as empty.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList object. Initialized TaskList will contain tasks provided in a list.
     *
     * @param tasks List of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks in list.
     *
     * @return Size of task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Adds a task into the list.
     *
     * @param task Task to be added into list
     */
    public void addToTasks(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a specified task from the list.
     *
     * @param taskNum Task to be removed from the list.
     * @throws DukeException If specified task number is out of range of task list.
     */
    public void deleteFromTasks(int taskNum) throws DukeException {
        if (taskNum < 0 || taskNum > tasks.size()) {
            throw new DukeException("Invalid task number.");
        }
        assert (taskNum > 0 && taskNum <= tasks.size()) : "Invalid task number.";
        tasks.remove(taskNum - 1);
    }

    /**
     * Sets a specified task from the list as done.
     *
     * @param taskNum Task number to be set as done.
     * @throws DukeException If specified task number is out of range of task list.
     */
    public void setAsDone(int taskNum) throws DukeException {
        if (taskNum < 0 || taskNum > tasks.size()) {
            throw new DukeException("Invalid task number.");
        }
        assert (taskNum > 0 && taskNum <= tasks.size()) : "Invalid task number.";
        tasks.get(taskNum - 1).markAsDone();
    }

    /**
     * Returns a specified task from the task number given.
     *
     * @param taskNum Task number to be returned.
     * @return Task to be returned.
     * @throws DukeException If specified task number is out of range of task list.
     */
    public Task getTask(int taskNum) throws DukeException {
        if (taskNum < 0 || taskNum > tasks.size()) {
            throw new DukeException("Invalid task number.");
        }
        assert (taskNum > 0 && taskNum <= tasks.size()) : "Invalid task number.";
        return tasks.get(taskNum - 1);
    }

    public TaskList findTasks(String searchTerm) {
        TaskList temp = new TaskList();

        for (Task t : this.tasks) {
            if (t.getDescription().contains(searchTerm)) {
                temp.addToTasks(t);
            }
        }
        return temp;
    }

    /**
     * Resets the task list so it is empty. For archiving purposes.
     */
    public void clean() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the string representation of a TaskList.
     * @return String representation of TaskList.
     */
    public String toString() {
        if (tasks.isEmpty()) {
            return "No tasks have been added!";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("    %d. %s\n", i + 1, tasks.get(i)));
        }
        return sb.toString();
    }
}
