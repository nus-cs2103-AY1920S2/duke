package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Class representing task list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates a TaskList object.
     */
    TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds the task to list.
     *
     * @param task task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Gets the task list.
     *
     * @return task list
     */
    public ArrayList<Task> getTaskList() {
        return tasks;
    }
}
