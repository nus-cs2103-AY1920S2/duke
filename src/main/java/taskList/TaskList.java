package taskList;

import task.Task;

import java.util.ArrayList;

/**
 * The main class that wraps the actual list of tasks and allows actions to be acted upon the tasks.
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * To add a Task to the list.
     *
     * @param t Task to be added.
     */
    public void add(Task t) {
        this.tasks.add(t);
    }

    public ArrayList<Task> getList() {
        return this.tasks;
    }

    /**
     * Remove task from list based on given index.
     * @param index Index of task to be removed from list.
     */
    public void remove(int index) {
        tasks.remove(index);
    }
}
