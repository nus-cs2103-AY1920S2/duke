package duke;

import java.util.ArrayList;

/**
 * Tasklist class to hold numerous Task objects.
 */
public class TaskList {

    private ArrayList<Task> lst;

    /**
     * Constructor method with underlying use of Java Collections.
     */
    public TaskList() {
        lst = new ArrayList<>();
    }

    /**
     * Overloaded constructor in the case of an existing collection of Tasks.
     * @param lst lst
     */
    public TaskList(ArrayList<Task> lst) {
        this.lst = lst;
    }

    /**
     * Returns task at index.
     * @param index index
     * @return Task
     */
    public Task get(int index) {
        return lst.get(index);
    }

    /**
     * Adds task e to tasklist.
     * @param e element
     */
    public void add(Task e) {
        lst.add(e);
    }

    /**
     * Removes task at index.
     * @param e element
     */
    public void remove(int e) {
        lst.remove(e);
    }

    /**
     * Gets number of tasks currently held.
     * @return int size
     */
    public int size() {
        return lst.size();
    }

    /**
     * Converts and returns an ArrayList of Tasks.
     * @return ArrayList of Tasks
     */
    public ArrayList<Task> toArr() {
        return lst;
    }
}
