package duke;

import java.util.ArrayList;
import java.util.stream.Stream;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Gets the size of the TaskList.
     * @return Number of items in TaskList.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Perform task at position specified by index.
     * @param index Position of task to perform.
     */
    public void doTask(int index) {
        this.tasks.get(index).doTask();
    }

    /**
     * Get task at specified index.
     * @param index Position of task to get.
     * @return Task at position index.
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Remove task at specified index.
     * @param index Position of task to remove.
     * @return Task that was removed.
     */
    public Task remove(int index) {
        return this.tasks.remove(index);
    }

    /**
     * Get stream representation of tasks in TaskList.
     * @return A stream of tasks in TaskList.
     */
    public Stream<Task> getTasksStream() {
        return tasks.stream();
    }

    /**
     * Add task to end of TaskList.
     * @param task Task to be added.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }
}
