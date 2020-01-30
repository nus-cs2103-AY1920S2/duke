import java.util.ArrayList;

/**
 * A list of user's tasks.
 */
public class TaskList {
    private  ArrayList<Task> tasks;

    /**
     * Constructs TaskList with given list of tasks.
     *
     * @param tasks A list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Gets total number of tasks in the list.
     *
     * @return Total number of tasks.
     */
    public int getTaskNumber() {
        return this.tasks.size();
    }

    /**
     * Gets target Task in the list with the certain index.
     *
     * @param index Index of target Task.
     * @return Target Task.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Adds certain Task into the list.
     *
     * @param t New Task.
     */
    public void addTask(Task t) {
        this.tasks.add(t);
    }

    /**
     * Removes certain Task from the list.
     *
     * @param t Removed Task.
     */
    public void removeTask(Task t) {
        this.tasks.remove(t);
    }
}
