package duke;

import java.util.ArrayList;

/**
 * The TaskList class contains the list of tasks. It has methods: addTask, deleteTask, size() and get.
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {

    }

    /**
     * Adds the task to the list.
     * @param t The task being added to the list
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Delete task at index in the list.
     * @param index the position of the task needed to be deleted
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * returns the size of the list.
     * @return the size of the list
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * returns the task at position index.
     * @param index the position of the desired task
     * @return the required task
     */
    public Task get(int index) {
        return tasks.get(index);
    }
}
