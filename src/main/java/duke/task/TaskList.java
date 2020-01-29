package duke.task;

import duke.exception.InvalidCommandException;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for the task list.
 * Contains operations for the task list.
 */
public class TaskList {
    /** List of tasks. */
    private List<Task> tasks = new ArrayList<>();

    /**
     * Constructs a new task list given a list of tasks.
     *
     * @param tasks a list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Sets a task in the list to be done based on the index.
     *
     * @param index the index of the task based on the list's string representation to be set as done.
     * @throws InvalidCommandException if the index given is out of bounds
     */
    public void setAsDone(int index) throws InvalidCommandException {
        if (index > tasks.size() || index <= 0) {
            throw new InvalidCommandException("     ☹ OOPS!!! I cannot set a "
                    + "non-existent task to be done.");
        }

        //mark selected task to be done
        tasks.get(index - 1).markDone();
    }

    /**
     * Deletes a task from the list based on the index and returns the task deleted.
     *
     * @param index the index of the task based on the list's string representation to be deleted.
     * @return the deleted task.
     * @throws InvalidCommandException if the index given is out of bounds.
     */
    public Task deleteTask(int index) throws InvalidCommandException {
        //check if index is valid
        if (index > tasks.size() || index <= 0) {
            throw new InvalidCommandException("     ☹ OOPS!!! I cannot delete a "
                    + "non-existent task.");
        }

        //delete the task from the list and return it
        Task taskToDelete = tasks.remove(index - 1);
        return taskToDelete;
    }

    /**
     * Gets the task from the list based on the index.
     *
     * @param index the index of the task based on the list's string representation to be retrieved.
     * @return the task of the respective index.
     */
    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    /**
     * Gets the list of all the tasks.
     *
     * @return the list of tasks.
     */
    public List<Task> getList() {
        return tasks;
    }

    /**
     * Gets the number of task in the list.
     *
     * @return the number of task currently in the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Gets the string representation of the task list.
     *
     * @return the string representation of the task list.
     */
    @Override
    public String toString() {
        String listRepresentation = "";
        for (int i = 0; i < tasks.size(); i++) {
            listRepresentation += String.format("     %d. %s\n", (i + 1), tasks.get(i));
        }
        return listRepresentation;
    }
}
