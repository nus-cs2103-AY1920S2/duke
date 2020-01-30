package duchess.task;

import duchess.exception.DuchessException;

import java.util.ArrayList;

/**
 * The {@code TaskList} object helps to store and manage {@code Task}s.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Initialises an empty {@code TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Initialises a {@code TaskList} containing existing tasks.
     *
     * @param tasks List of existing tasks to be included in the {@code TaskList}.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of {@code Task}s in the {@code TaskList}.
     *
     * @return Number of tasks in {@code TaskList}.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Adds a {@code Task} to the {@code TaskList}.
     *
     * @param task {@code Task} to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes a {@code Task} from the {@code TaskList}.
     *
     * @param index Index of {@code Task} to be removed.
     */
    public void removeTask(int index) {
        this.tasks.remove(index);
    }

    /**
     * Returns a {@code Task} from the {@code TaskList} based on its {@code index}.
     *
     * @param index Index of {@code Task} to be retrieved.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Completes a {@code Task} at {@code index} in the {@code TaskList}.
     *
     * @param index Index of {@code Task} in the {@code TaskList}.
     * @return {@code Task} at the given index.
     * @throws DuchessException If the task has already been completed.
     */
    public Task completeTask(int index) throws DuchessException {
        Task taskToComplete = this.getTask(index);
        if (taskToComplete.isCompleted()) {
            throw new DuchessException("You have already completed this task!");
        }
        taskToComplete.toggleIsCompleted();
        return taskToComplete;
    }

    /**
     * Returns the entire {@code TaskArray}.
     *
     * @return The list of tasks in {@code ArrayList<Task>}.
     */
    public ArrayList<Task> getTaskArray() {
        return this.tasks;
    }
}
