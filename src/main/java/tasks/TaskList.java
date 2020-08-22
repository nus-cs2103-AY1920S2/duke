package tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskList object stores the List of tasks by the user in Duke. Tasklist object handles
 * operations of adding Task object, deleting Task object and retrieving Task object.
 */
public class TaskList {
    /**
     * Class TaskList contains List of Task objects to be maintained.
     */
    private List<Task> tasks;
    private Task mostRecentTask;

    /**
     * Constructor for Tasklist object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs new TaskList object with input List of Task objects.
     * @param tasks List of Task objects used to populate TaskList's list parameter.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Deletes Task object at index from list.
     * @param index Index of Task object to be deleted.
     */
    public void deleteTask(int index) {
        assert index <= tasks.size() && index > 0 : "Index of "
                + "task should be greater than 0";
        mostRecentTask = this.tasks.get(index);
        this.tasks.remove(index);
    }

    /**
     * Adds Task object to list.
     * @param task Task object to be added.
     */
    public void addTask(Task task) {
        assert task != null : "Task should not be null.";
        this.tasks.add(task);
        mostRecentTask = task;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns Task object from list at index.
     * @param index Index of desired Task object.
     * @return Task object at index.
     */
    public Task getTask(int index) {
        assert index <= tasks.size() && index > 0 : "Index should be greater"
                + " than 0 and smaller than list size.";
        return this.tasks.get(index);
    }

    /**
     * Duke marks Task as done.
     * @param index Index which task to be marked is located.
     */
    public void markAsDone(int index) {
        Task task = tasks.get(index);
        task.markAsDone();
        mostRecentTask = task;
    }

    /**
     * Checks size of list.
     * @return Returns number of Task objects in the list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Checks if the list contains no elements.
     * @return True if the list contains no elements.
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            sb.append("  ").append(i).append(". ")
                    .append(tasks.get(i - 1)).append("\n");
        }
        assert sb.length() > 0 : "StringBuilder should not be empty.";
        return sb.toString();
    }

    /**
     * Constructs a new list to store search results.
     * @param searchKey Identifier that Task details needs to have.
     * @return Task objects that contain identifier.
     */
    public List<Task> find(String searchKey) {
        // Create new List<Task> to hold search results.
        List<Task> results = new ArrayList<>();
        for (Task t: tasks) {
            if (t.getDescription().contains(searchKey)) {
                results.add(t);
            }
        }
        return results;
    }

    /**
     * Removes most recently added Task from list.
     */
    public void undoAdd() {
        tasks.remove(this.getSize() - 1);
    }

    /**
     * Undoes completion mark on Task.
     */
    public void undoDone() {
        mostRecentTask.undoDone();
    }

    /**
     * Undoes deletion of task.
     * @param removedIndex Previous index of task before it was removed.
     */
    public void undoDelete(int removedIndex) {
        tasks.add(removedIndex - 1, mostRecentTask);
    }

    public Task getMostRecentTask() {
        return mostRecentTask;
    }
}
