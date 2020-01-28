package duke.tasklist;

import duke.DukeException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>TaskList Class</h1>
 * Records the tasks input by user.
 *
 * @author  Eng Xuan En
 */
public class TaskList {
    protected List<Task> records;

    /**
     * Class constructor of TaskList.
     */
    public TaskList() {
        records = new ArrayList<>();
    }

    /**
     * Constructor of TaskList which takes in List of Tasks.
     * @param tasks tasks to be added into listing
     */
    public TaskList(List<Task> tasks) {
        records = tasks;
    }

    /**
     * Add task into listing.
     * @param task task to be added
     */
    public void addTask(Task task) {
        records.add(task);
    }

    /**
     * Set the task as Done.
     * @param num index where the task located at
     * @throws DukeException when no task found in that index
     */
    public void markDone(int num) throws DukeException {
        try {
            Task task = records.get(num - 1);
            task.setStatusDone();
            records.set(num - 1, task);
        } catch(IndexOutOfBoundsException e) {
            throw new DukeException("No task found in that index!");
        }
    }

    /**
     * Delete the task at the index.
     * @param num index where the task located at
     * @return task that being deleted
     * @throws DukeException when no task found in that index
     */
    public Task deleteTask(int num) throws DukeException {
        Task task;
        try {
            task = records.get(num - 1);
            records.remove(num - 1);
        } catch(IndexOutOfBoundsException e) {
            throw new DukeException("No task found in that index!");
        }
        return task;
    }

    /**
     * Find the tasks in listing that have the keyword in their description.
     * @param keyword keyword to find
     * @return List of tasks that found with the keyword in their description
     */
    public List<Task> findTask(String keyword) {
        List<Task> result = new ArrayList<>();
        for(Task task : records) {
           if(task.getDescription().contains(keyword)) {
               result.add(task);
           }
        }
        return result;
    }

    /**
     * Get the listing in listing.
     * @return listing in TaskList
     */
    public List<Task> getListing() {
        return records;
    }

    /**
     * Get task at specific index.
     * @param num index the task locate at
     * @return Task at the index
     */
    public Task getTask(int num) {
        return records.get(num - 1);
    }

    /**
     * Get the total amount of task in record.
     * @return the amount of task in record
     */
    public int getAmountOfTask() {
        return records.size();
    }
}
