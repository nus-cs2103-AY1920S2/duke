package duchess.task;

import duchess.exception.DuchessException;
import duchess.util.Pair;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
     * @throws DuchessException If the index is out of bounds.
     */
    public void removeTask(int index) throws DuchessException {
        try {
            this.tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DuchessException("You're referring to a task which does not exist!");
        }
    }

    /**
     * Returns a {@code Task} from the {@code TaskList} based on its {@code index}.
     *
     * @param index Index of {@code Task} to be retrieved.
     * @return {@code Task} at index given.
     * @throws DuchessException If the index is out of bounds.
     */
    public Task getTask(int index) throws DuchessException {
        try {
            return this.tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DuchessException("You're referring to a task which does not exist!");
        }
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

    /**
     * Returns a list of pairs of {@code Task}s and their index in the original
     * list. This allows the user to see the list with new indices while being
     * able to delete or complete tasks using the original indices.
     *
     * @param searchWords Word(s) to search for in the tasks' descriptions.
     * @return An array of pairs of {@code Task}s and {@code Integer}s. Returns an
     *         empty array if no tasks meet the requirement.
     */
    public ArrayList<Pair<Task, Integer>> find(String searchWords) {
        return IntStream.range(0, this.tasks.size())
                .mapToObj(i -> new Pair<>(this.tasks.get(i), i))
                .filter(p -> p.getFirst().getDescription().toLowerCase().contains(searchWords.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
