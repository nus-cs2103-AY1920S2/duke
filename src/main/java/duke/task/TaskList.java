package duke.task;

import java.util.ArrayList;
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
     * Adds given task and returns task addition success status.
     *
     * @param task to be added
     * @return task addition success status
     */
    public boolean addTask(Task task) {
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
