package dude.component;

import dude.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskList is a thin wrapper over an ArrayList&lt;Task&gt; to hide implementation details.
 * Notably, it provides 1-based indexing to mirror user input.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>(100);
    }

    /**
     * Retrieves all the tasks currently in the list, according to their insertion order.
     * A copy is made to prevent the private field from being exposed and possibly mutated externally.
     *
     * @return A copy of the internal taskList.
     */
    public List<Task> getAllTasks() {
        return List.copyOf(this.taskList);
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
     * Formats the current list of tasks into a form meant to be understood by users.
     * Shows the task index next to each task to let users identify the indices,
     * if they want to perform done or delete tasks. Task display format uses the Task.toString()
     * methods.
     *
     * @return an array of formatted strings which display each tasks' index and details.
     * @see dude.task.Todo#toString()
     * @see dude.task.Deadline#toString()
     * @see dude.task.Event#toString()
     */
    public String[] showAllTasks() {
        String[] result = new String[taskCount()];
        for (int i = 1; i <= taskCount(); i++) {
            result[i-1] = String.format("%d.%s", i, getTask(i));
        }
        return result;
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
