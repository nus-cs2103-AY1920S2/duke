package duke.task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaskList implements Serializable {
    private List<Task> taskList;

    /**
     * Default constructor for TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Copy constructor for TaskList.
     *
     * @param other The TaskList to copy from.
     */
    public TaskList(TaskList other) {
        if (other == null) {
            this.taskList = new ArrayList<>();
        } else {
            this.taskList = new ArrayList<>(other.taskList);
        }
    }

    /**
     * Adds a new task to the TaskList.
     *
     * @param task The new task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Removes a task from the TaskList.
     *
     * @param task A reference to the task to be removed.
     */
    public void removeTask(Task task) {
        removeAtIndex(taskList.indexOf(task));
    }

    /**
     * Removes all tasks from the TaskList.
     */
    public void removeAllTask() {
        taskList.clear();
    }

    /**
     * Removes a task from the TaskList by index.
     *
     * @param index The index of the task to be removed.
     * @return A reference to the removed task.
     */
    public Task removeAtIndex(int index) {
        return taskList.remove(index);
    }

    /**
     * Mark the task at a index as done.
     *
     * @param index The index of the task to be marked as done.
     */
    public void markAsDone(int index) {
        taskList.get(index).setIsDone(true);
    }

    /**
     * Returns the task at a index.
     *
     * @param index The index of the task to be returned.
     * @return The task at the given index.
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Returns the number of elements in the TaskList.
     *
     * @return The number of elements in the TaskList.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Checks if a given index is within the bounds of the taskList collection.
     *
     * @param index The index to be checked.
     * @return true if the input index is valid; false otherwise.
     */
    public boolean isIndexValid(int index) {
        return index >= 0 && index < taskList.size();
    }

    /**
     * Returns an array of all the names of tasks in the taskList.
     * <p>
     * Certain procedures (e.g., Printing of all tasks) requires access to the names of each
     * element in the taskList. This function returns all the names of tasks (from their respective
     * toString() functions) as an array.
     *
     * @return An array containing the names of every duke.task in the taskList.
     */
    public String[] getNames() {
        String[] names = new String[taskList.size()];
        for (int i = 0; i < names.length; ++i) {
            names[i] = taskList.get(i).toString();
        }
        return names;
    }

    /**
     * Fuzzy searches all tasks in the TaskList for tasks that match the input search query.
     *
     * @param searchQuery The search query.
     * @return A list of descriptions of tasks that matches the input search query.
     */
    public List<String> findTasks(String searchQuery) {

        String[] args = searchQuery.split(" ");
        String regexTemplate = "(?=.*\\b%s\\b)";
        String regex = ".*";

        for (String arg : args) {
            regex = String.format(regexTemplate, arg) + regex;
        }

        List<String> matchingTasks = new ArrayList<>();
        for (int i = 0; i < taskList.size(); ++i) {
            if (taskList.get(i).description.matches(regex)) {
                matchingTasks.add(taskList.get(i).description);
            }
        }

        return matchingTasks;
    }
}
