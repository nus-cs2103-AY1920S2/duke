package duke.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Contains the task list.
 * e.g. it has operations to add/delete tasks in the list
 */
public class TaskList implements Iterable<Task> {
    protected List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Getter for tasks.
     *
     * @return tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Setter for TaskList.
     *
     * @param tasks to be used for modification
     */
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds given task and returns task addition success status.
     *
     * @param task to be added
     * @return task addition success status
     */
    public boolean addTask(Task task) {
        if (task == null) {
            return false;
        }
        return tasks.add(task);
    }

    /**
     * Removes task from list at given index.
     *
     * @param index Task number to be removed (zero-based numbering)
     * @return Task that was removed at given index
     * @throws IndexOutOfBoundsException Index given is not a valid list index
     */
    public Task remove(int index) throws IndexOutOfBoundsException {
        Task removedTask = tasks.get(index);
        tasks.remove(index);
        return removedTask;
    }

    /**
     * Returns the task at a given index.
     *
     * @param index For retrieving tasks at specified list position
     * @return Task obtained at given index
     * @throws IndexOutOfBoundsException Index given is not a valid list index
     */
    public Task get(int index) throws IndexOutOfBoundsException {
        return tasks.get(index);
    }

    /**
     * Returns the total number of tasks stored.
     *
     * @return The total number of Tasks in list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Creates and returns a copy of this object.
     */
    public TaskList copy() {
        List<Task> copyTasks = new ArrayList<>(tasks);
        return new TaskList(copyTasks);
    }

    /**
     * Returns a hash code value for the object. This method is
     * supported for the benefit of hash tables such as those provided by
     * {@link HashMap}.
     *
     * @return a hash code value for this object.
     * @see Object#equals(Object)
     * @see System#identityHashCode
     */
    @Override
    public int hashCode() {
        // Solution adapted from
        // @@author Javin Paul from Javarevisited (February 23, 2017)
        // https://javarevisited.blogspot.com/2011/02/how-to-write-equals-method-in-java.html
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((tasks == null) ? 0 : tasks.hashCode());
        return result;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     *     argument; {@code false} otherwise.
     * @see #hashCode()
     * @see HashMap
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        TaskList taskList = (TaskList) obj;
        int taskListSize = taskList.size();
        if (taskListSize != this.size()) {
            return false;
        }
        for (int i = 0; i < taskListSize; i++) {
            Task t1 = taskList.get(i);
            Task t2 = this.get(i);
            if (!t1.getDescription().equals(t2.getDescription())) {
                return false;
            }
            if (!t1.getStatusIcon().equals(t2.getStatusIcon())) {
                return false;
            }
            if (!t1.stringToSaveToDisk().equals(t2.stringToSaveToDisk())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns an iterator over elements of type {@code Task}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

    @Override
    public void forEach(Consumer<? super Task> action) {
        tasks.forEach(action);
    }

    @Override
    public Spliterator<Task> spliterator() {
        return tasks.spliterator();
    }
}
