package sharadhr.duke.task;

import java.util.ArrayList;
import java.util.List;

import sharadhr.duke.Duke;
import sharadhr.duke.io.Output;

/**
 * A list of tasks, and methods to interact with that list (add, delete, find, enumerate through).
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates an empty {@link TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Creates a {@link TaskList} with tasks initialised from {@code tasks}.
     *
     * @param tasks the list of {@link Task}s to initialise this TaskList with
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<Task>(tasks);
    }

    /**
     * Adds a task to the list.
     *
     * @param task A task to be added to the list.
     * @return {@code true} if task was successfully added (as specified by
     * {@link ArrayList#add})
     */
    public boolean addTask(Task task) {
        // Was the task successfully added to the list?
        boolean added = this.tasks.add(task);

        Duke.output.sayTaskAdded(task);
        Duke.fileRW.appendTaskToFile(task);

        return added;
    }

    private Task getTaskAtIndex(int index) throws IndexOutOfBoundsException {
        return tasks.get(index);
    }

    /**
     * Returns a task at {@code position}.
     *
     * @param position The 1-indexed position of the task in the list
     * @return The task at the specified {@code position}
     * @throws IndexOutOfBoundsException if {@code position} ≥ size of tasks list +
     *                                   1
     */
    public Task getTaskAtPosition(int position) throws IndexOutOfBoundsException {
        return this.getTaskAtIndex(position - 1);
    }

    /**
     * Returns a {@link Task}[], whose {@code detail} {@link String}s contain the
     * {@code keyword}.
     *
     * @param keyword the keyword to look up
     * @return the tasks that have been found
     */
    public Task[] findTasksWithKeyword(String keyword) {
        return this.tasks.stream().dropWhile(x -> !x.detail.contains(keyword)).toArray(Task[]::new);
    }

    /**
     * Deletes the task at the specified {@code position}.
     *
     * @param position the (1-indexed) position of the task to be deleted
     */
    public void deleteTaskAtPosition(int position) {
        tasks.remove(position - 1);
    }

    /**
     * Prints the tasks in this list.
     *
     * @param output the {@link Output} object to use to print with
     */
    public void listTasks(Output output) {
        if (!this.tasks.isEmpty()) {
            output.say("Here are the tasks in your list:");

            int listNumber = 1;
            for (Task task : this.tasks) {
                output.add(String.format("%d.%s%n", listNumber++, task));
            }
            output.say();
        } else {
            output.say("You have no tasks in your list.");
        }
    }

    /**
     * Returns the number of tasks in this {@link TaskList}.
     *
     * @return The number of tasks
     */
    public int numberOfTasks() {
        return this.tasks.size();
    }
}
