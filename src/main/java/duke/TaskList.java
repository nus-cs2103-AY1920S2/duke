package duke;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents the task-list containing all Tasks the user would record.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Creates a new Task-List object.
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Adds a new Task to the list.
     *
     * @param t Task to be added.
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Removes a Task from the list.
     *
     * @param index Index of Task from the task-list to remove.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Retrieves a certain Task from the list.
     *
     * @param index Index of Task from the task-list to retrieve.
     * @return The task extracted from the task-list.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Looks up a Task or set of Tasks whose name matches a certain keyword.
     *
     * @param keyword String to search Tasks by.
     * @return Task-List containing Tasks whose names match the keyword.
     */
    public TaskList findByKeyword(String keyword) {
        TaskList foundTaskList = new TaskList();

        for (Task t : tasks) {
            if (t.getName().contains(keyword)) {
                foundTaskList.addTask(t);
            }
        }

        return foundTaskList;
    }

    /**
     * Returns the total number of Tasks in the task-list.
     *
     * @return The number of Tasks in the task-list.
     */
    public int getNumTasks() {
        return tasks.size();
    }

    /**
     * Marks a Task as having the done status.
     *
     * @param index Index of Task from the task-list to mark as done.
     */
    public void markAsDone(int index) {
        tasks.get(index).markAsDone();
    }
}
