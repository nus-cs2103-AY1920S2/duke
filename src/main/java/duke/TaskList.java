package duke;

import duke.exception.DukeException;
import duke.exception.DukeInvalidIndexException;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents a TaskList that contains a list of tasks.
 */
public class TaskList {

    protected ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with given list of tasks.
     *
     * @param tasks The given list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs a TaskList with the list of tasks being initialised.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Adds a task to the end of the list of tasks.
     *
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the task at the specified position in the list of tasks.
     *
     * @param index The specified position of the task to be removed.
     * @return The task that was removed from the list.
     * @throws DukeException If the index is out of range.
     */
    public Task removeTask(int index) throws DukeException {
        try {
            return tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidIndexException();
        }
    }

    /**
     * Returns the task at the specified position in the list of tasks.
     *
     * @param index The specified position of the task.
     * @return The task at the specified position in the list.
     * @throws DukeException If the index is out of range.
     */
    public Task getTask(int index) throws DukeException {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidIndexException();
        }
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks which match the keyword.
     *
     * @param keyword The keyword to match.
     * @return The list of tasks which match the keyword.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> filtered = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.getDescription().contains(keyword)) {
                filtered.add(task);
            }
        }
        return filtered;
    }
}
