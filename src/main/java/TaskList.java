/*package duke;

import duke.Ui;*/
import java.util.ArrayList;

/**
 * Represents the task list that stores all current tasks.
 */
public class TaskList {
    /**
     * The list of task.
     */
    private ArrayList<Task> tasks;
    /**
     * The user interface.
     */
    private Ui ui;

    /**
     * Creates a new TaskList with the given arraylist that contains tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.ui = new Ui();
    }

    /**
     * Creates an empty TaskList (with maximum capacity 100).
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>(100);
        this.ui = new Ui();
    }

    /**
     * Gets the task list.
     * @return list of this TaskList.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Adds a task to this tasklist.
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        ui.add(task, tasks);
    }

    /**
     * Marks a task in this list as done.
     * @param n Index of the task that is done.
     */
    public void doneTask(int n) {
        tasks.get(n-1).setDone();
        ui.done(n, tasks);
    }

    /**
     * Deletes a task in this list.
     * @param n Index of the task to be deleted.
     */
    public void deleteTask(int n) {
        ui.delete(n, tasks);
        tasks.remove(n-1);
        ui.count(tasks);
    }

    /**
     * Prints the list of tasks.
     */
    public void printList() {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) != null) {
                System.out.println((i + 1) + ". " + tasks.get(i).toString());
            }
        }
    }
}