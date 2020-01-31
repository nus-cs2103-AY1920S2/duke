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

    /**
     * Performs the given action for each element of the {@code Iterable}
     * until all elements have been processed or the action throws an
     * exception.  Actions are performed in the order of iteration, if that
     * order is specified.  Exceptions thrown by the action are relayed to the
     * caller.
     * <p>
     * The behavior of this method is unspecified if the action performs
     * side-effects that modify the underlying source of elements, unless an
     * overriding class has specified a concurrent modification policy.
     *
     * @param action The action to be performed for each element
     * @throws NullPointerException if the specified action is null
     * @implSpec <p>The default implementation behaves as if:
     * <pre>{@code
     *     for (T t : this)
     *         action.accept(t);
     * }</pre>
     * @since 1.8
     */
    @Override
    public void forEach(Consumer<? super Task> action) {
        tasks.forEach(action);
    }

    /**
     * Creates a {@link Spliterator} over the elements described by this
     * {@code Iterable}.
     *
     * @return a {@code Spliterator} over the elements described by this
     * {@code Iterable}.
     * @implSpec The default implementation creates an
     * <em><a href="../util/Spliterator.html#binding">early-binding</a></em>
     * spliterator from the iterable's {@code Iterator}.  The spliterator
     * inherits the <em>fail-fast</em> properties of the iterable's iterator.
     * @implNote The default implementation should usually be overridden.  The
     * spliterator returned by the default implementation has poor splitting
     * capabilities, is unsized, and does not report any spliterator
     * characteristics. Implementing classes can nearly always provide a
     * better implementation.
     * @since 1.8
     */
    @Override
    public Spliterator<Task> spliterator() {
        return tasks.spliterator();
    }
}
