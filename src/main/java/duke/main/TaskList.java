package duke.main;

import duke.task.Task;
import java.util.ArrayList;

/**
 * Contains the task list. Has operations to add and delete tasks in the list.
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<String> list, Parser parser) {
        this.tasks = parser.parseSavedFile(list);
    }

    /**
     * Adds a task to the task list.
     *
     * @param t Task to be added
     */
    public void addToTaskList(Task t) {
        tasks.add(t);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index Index of the item in the task list to be deleted
     */
    public void deleteFromTaskList(int index) {
        tasks.remove(index);
    }

    /**
     * Gets the task list.
     *
     * @return Array list of tasks
     */
    public ArrayList<Task> getTaskList() {
        return tasks;
    }
}
