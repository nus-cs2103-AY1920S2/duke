package duke;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.List;

/**
 * This class wraps around the class ArrayList from Java
 * Collection Framework.
 **/
public class TaskList {
    public ArrayList<Task> taskList;


    /**
     * Constructor for an empty array list.
     **/
    TaskList() {
        ArrayList<Task> taskList = new ArrayList<Task>();
    }

    /**
     * Private constructor of a non-empty array list.
     * @param taskList The ArrayList of this self-implemented TaskList.
     **/
    TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Set an object into the ArrayList at a specific index
     * following the set() method of Java's ArrayList.
     * @param t The task to be added
     * @param i The index to be inserted
     **/
    public void set(int i, Task t) {
        this.taskList.set(i, t);
    }

    /**
     * Get a task object from the ArrayList following the get() method of Java's ArrayList.
     * @param i The indec of the task to be taken
     **/
    public Task get(int i) {
        return this.taskList.get(i);
    }

    /**
     * Remove an object into the ArrayList following the remove() method of Java's ArrayList.
     * @param t The task to be added
     **/
    public void remove(Task t) {
        this.taskList.remove(t);
    }

    /**
     * Calculate the size of the ArrayList following the size() method of Java's ArrayList.
     **/
    public int size() {
        return this.taskList.size();
    }

    /**
     * Add an object into the ArrayList following the add() method of Java's ArrayList.
     * @param t The task to be added
     **/
    public void add(Task t) {
        this.taskList.add(t);
    }

    /**
     * Obtain the list embedded in this TaskList.
     * @return An ArrayList of this TaksList
     **/
    public ArrayList<Task> getTasks() {
        return this.taskList;
    }

    /**
     * Filter the list of task to the one that contains a certain string.
     * @return A TaskList that has been filtered
     **/
    public TaskList filter(String s) {
        ArrayList<Task> al = new ArrayList<>(
                this.taskList.stream()
                        .filter(x -> x.getName().contains(s)).collect(Collectors.toList()));
        return new TaskList(al);
    }
}
