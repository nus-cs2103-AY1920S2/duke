package duke.tasks;

import java.util.List;
import java.util.ArrayList;

import duke.tasks.Task;

/**
 * Represents a list of <code>Task</code>s.
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
     * Adds a <code>Task</code> to the TaskList.
     * 
     * @param task Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Adds multiple <code>Task</code>s to the TaskList.
     * 
     * @param moreTasks Tasks to be added.
     */
    public void add(List<Task> moreTasks) {
        tasks.addAll(moreTasks);
    }

    /**
     * Retrives a specified <code>Task</code> from the TaskList.
     * 
     * @param taskNo <code>Task</code> number to be retrieved.
     * @return <code>Task</code> object specified.
     */
    public Task get(int taskNo) {
        return tasks.get(taskNo - 1);
    }

    /**
     * Removes a specified <code>Task</code> from the TaskList
     * 
     * @param taskNo <code>Task</code> number to be removed.
     * @return <code>Task</code> object that has been removed.
     */
    public Task remove(int taskNo) {
        return tasks.remove(taskNo - 1);
    }

    /**
     * Retrives all <code>Task</code>s from the TaskList.
     * 
     * @return <code>List</code> of all Tasks in the TaskList.
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