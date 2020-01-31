import java.util.ArrayList;

/**
 * Manages all the tasks in Duke.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns all tasks currently managed by Duke.
     * @return Array of tasks in Duke.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns the number of tasks currently managed by Duke.
     * @return Number of tasks in Duke.
     */
    public int getNumTasks() {
        return this.tasks.size();
    }

    /**
     * Add a task to Duke.
     * @param t Task to be added.
     */
    public void addTask(Task t) {
        this.tasks.add(t);
    }

    /**
     * Remove a task from Duke.
     * @param i Index of task to be removed.
     * @return Task that was removed.
     */
    public Task removeTask(int i) {
        Task t = this.tasks.get(i);
        this.tasks.remove(i);
        return t;
    }

    /**
     * Mark a task in Duke as done.
     * @param i Index of task to be marked as done.
     * @return Task that was marked as done.
     */
    public Task markTaskAsDone(int i) {
        Task t = this.tasks.get(i);
        t.setDone();
        return t;
    }

    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task t: this.arrTasks) {
            if (t.getDescription().contains(keyword)) {
                tasks.add(t.getCopy());
            }
        }
        return tasks;
    }
}
