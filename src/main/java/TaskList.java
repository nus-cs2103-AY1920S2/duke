import java.util.ArrayList;

/**
 * Manages all the tasks in Duke.
 */
public class TaskList {
    private ArrayList<Task> arrTasks;

    public TaskList() {
        this.arrTasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> arrTasks) {
        this.arrTasks = arrTasks;
    }

    /**
     * Returns all tasks currently managed by Duke.
     * @return Array of tasks in Duke.
     */
    public ArrayList<Task> getTasks() {
        return this.arrTasks;
    }

    /**
     * Returns the number of tasks currently managed by Duke.
     * @return Number of tasks in Duke.
     */
    public int getNumTasks() {
        return this.arrTasks.size();
    }

    /**
     * Add a task to Duke.
     * @param t Task to be added.
     */
    public void addTask(Task t) {
        this.arrTasks.add(t);
    }

    /**
     * Remove a task from Duke.
     * @param i Index of task to be removed.
     * @return Task that was removed.
     */
    public Task removeTask(int i) {
        Task t = this.arrTasks.get(i);
        this.arrTasks.remove(i);
        return t;
    }

    /**
     * Mark a task in Duke as done.
     * @param i Index of task to be marked as done.
     * @return Task that was marked as done.
     */
    public Task markTaskAsDone(int i) {
        Task t = this.arrTasks.get(i);
        t.setDone();
        return t;
    }
}
