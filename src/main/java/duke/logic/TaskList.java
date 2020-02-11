package duke.logic;

import duke.task.DateTimeTask;
import duke.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

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
     * Return a specified task managed by Duke.
     * @param i Task index.
     * @return Requested task.
     */
    public Task getTask(int i) {
        return this.tasks.get(i).getCopy();
    }

    /**
     * Returns all tasks currently managed by Duke.
     * @return Array of tasks in Duke.
     */
    public ArrayList<Task> getTasks() {
        ArrayList<Task> tasksCopy = new ArrayList<>();
        for (Task t: this.tasks) {
            tasksCopy.add(t.getCopy());
        }
        return tasksCopy;
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
        // No error means idx is in range and task has been assigned
        assert (i >= 0 && i < getNumTasks()) && (t != null);
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
        // No error means idx is in range and task has been assigned
        assert (i >= 0 && i < getNumTasks()) && (t != null);
        t.setDone();
        return t;
    }

    /**
     * Find all tasks containing a given keyword.
     * @param keyword Keyword to find.
     * @return Array of tasks containing the keyword.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task t: this.tasks) {
            if (t.getDescription().contains(keyword)) {
                tasks.add(t.getCopy());
            }
        }
        return tasks;
    }

    /**
     * Edit the description of a task.
     * @param i Task index.
     * @param desc New description.
     * @return Task which was replaced.
     */
    public Task editTask(int i, String desc) {
        Task t = this.tasks.get(i).getCopy();
        this.tasks.get(i).setDescription(desc);
        return t;
    }

    /**
     * Edit the date of a task.
     * @param i Task index.
     * @param date New date.
     * @return Task which was replaced.
     */
    public Task editTask(int i, LocalDate date) {
        assert this.tasks.get(i) instanceof DateTimeTask;
        Task t = this.tasks.get(i).getCopy();
        ((DateTimeTask)this.tasks.get(i)).setDateTime(date);
        return t;
    }

    /**
     * Edit the description and date of a task.
     * @param i Task index.
     * @param desc New description.
     * @param date New date.
     * @return Task which was replaced.
     */
    public Task editTask(int i, String desc, LocalDate date) {
        assert this.tasks.get(i) instanceof DateTimeTask;
        Task t = this.tasks.get(i).getCopy();
        this.tasks.get(i).setDescription(desc);
        ((DateTimeTask)this.tasks.get(i)).setDateTime(date);
        return t;
    }
}
