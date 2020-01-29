package duke.task;

import duke.exception.DukeException;

import java.util.ArrayList;

/**
 * Represents a list of tasks
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * @param tasks list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Add a new task to the list.
     *
     * @param task new task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Search and get a task by the given index.
     *
     * @param index given index of the task
     * @return the result task
     * @throws DukeException exception for error while the given index not exist
     */
    public Task getByIndex(int index) throws DukeException {
        try {
            return tasks.get(index - 1);
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("OOPS!!! We don't have this number in list!!!");
        }
    }

    /**
     * Get the size of the list.
     *
     * @return integer size of the list
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Get the list from the TaskList object.
     *
     * @return list of tasks
     */
    public ArrayList<Task> getList() {
        return tasks;
    }

    /**
     * Delete the task from the list based on the given index.
     *
     * @param index given index of the task
     * @return deleted task
     * @throws DukeException exception for error while the given index not exist
     */
    public Task deleteTaskByIndex(int index) throws DukeException{
        try {
            return tasks.remove(index - 1);
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("OOPS!!! We don't have this number in list!!!");
        }
    }
}
