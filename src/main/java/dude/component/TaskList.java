package dude.component;

import dude.task.Task;

import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * TaskList is a thin wrapper over an {@code ArrayList<Task>} to hide implementation details.
 * Notably, it provides 1-based indexing to mirror user input.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /** A nested class that carries both Task information and the Task's position in the TaskList. */
    private static class TaskListEntry {
        public int index;
        public Task task;

        public TaskListEntry(int index, Task task) {
            this.index = index;
            this.task = task;
        }
    }

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>(100);
    }

    /**
     * Adds a task to the end of the current task list, with its index being the length of the list.
     *
     * @param task Task to be added to the task list.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Retrieves the index-th task in the current task list, counting from 1 being the earliest task to be added.
     *
     * @param index Index of task to be retrieved.
     * @return Task at given index.
     * @throws IndexOutOfBoundsException If index &lt; 1 or index &gt; taskCount().
     */
    public Task getTask(int index) {
        return this.taskList.get(index - 1);
    }

    /**
     * Deletes the index-th task from the current task list, counting from 1 being the earliest task to be added.
     * Decreases taskCount() by 1.
     *
     * @param index Index of task to be removed.
     * @return the removed task.
     * @throws IndexOutOfBoundsException If index &lt; 1 or index &gt; taskCount().
     */
    public Task removeTask(int index) {
        return this.taskList.remove(index - 1);
    }

    /**
     * Produces a stream of filtered and formatted Task entry Strings for calling methods to consume.
     *
     * @param formatter bifunction that takes the index and task and produces the formatted Task string.
     * @param predicate the filtering function.
     * @return a stream of filtered and formatted Task entry Strings.
     */
    public Stream<String> formatFilteredTasks(
            BiFunction<Integer, Task, String> formatter,
            Predicate<Task> predicate) {

        return IntStream.rangeClosed(1, taskCount())
                .mapToObj(index -> new TaskListEntry(index, getTask(index)))
                .filter(entry -> predicate.test(entry.task))
                .map(entry -> formatter.apply(entry.index, entry.task));
    }

    /**
     * Produces a stream of filtered and formatted Task entry Strings for calling methods to consume.
     * Convenience method over <code>formatFilteredTasks</code> that uses default user-facing formatting
     * {@code "<index>.<task.toString()>" }.
     *
     * @param predicate the filtering function.
     * @return a stream of filtered and formatted Task entry Strings.
     */
    public Stream<String> showFilteredTasks(Predicate<Task> predicate) {
        return formatFilteredTasks((index, task) -> String.format("%d.%s", index, task), predicate);
    }

    /**
     * Returns the number of tasks currently in the list.
     *
     * @return number of tasks currently in this list.
     */
    public int taskCount() {
        return this.taskList.size();
    }
}
