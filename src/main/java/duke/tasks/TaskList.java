package duke.tasks;

import java.util.List;
import java.util.ArrayList;

import duke.tasks.Task;

/**
 * Represents a list of Tasks.
 */
public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a Task to the TaskList.
     * 
     * @param task Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Adds multiple Tasks to the TaskList.
     * 
     * @param moreTasks Tasks to be added.
     */
    public void add(List<Task> moreTasks) {
        tasks.addAll(moreTasks);
    }

    /**
     * Retrives all Tasks containing a keyword from the TaskList.
     * 
     * @param keyword Keyword to be checked (case-insensitive).
     * @return List of Tasks containing the keyword.
     */
    public List<Task> search(String keyword) {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            // Case insensitive check
            if (task.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(task);
            }
        }
        return result;
    }

    /**
     * Retrives a specified Task from the TaskList.
     * 
     * @param taskNo Task number to be retrieved.
     * @return Task object specified.
     */
    public Task get(int taskNo) {
        return tasks.get(taskNo - 1);
    }

    /**
     * Removes a specified Task from the TaskList.
     * 
     * @param taskNo Task number to be removed.
     * @return Task object that has been removed.
     */
    public Task remove(int taskNo) {
        return tasks.remove(taskNo - 1);
    }

    /**
     * Retrives all Task from the TaskList.
     * 
     * @return List of all Tasks in the TaskList.
     */
    public List<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Gets the size of the TaskList.
     * 
     * @return Size of TaskList.
     */
    public int size() {
        return tasks.size();
    }
}