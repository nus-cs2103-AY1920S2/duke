package duke.tasklist;

import duke.DukeException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    protected List<Task> records;

    /**
     * Constructor of TaskList
     */
    public TaskList() {
        records = new ArrayList<>();
    }

    /**
     * Constructor of TaskList
     * @param tasks tasks to be added into records
     */
    public TaskList(List<Task> tasks) {
        records = tasks;
    }

    /**
     * Add task into database
     * @param task task to be added
     */
    public void addTask(Task task) {
        records.add(task);
    }

    /**
     * Set the task as Done
     * @param num index where the task at
     * @throws DukeException when no task found in that index
     */
    public void markDone(int num) throws DukeException{
        try {
            Task task = records.get(num - 1);
            task.setStatusDone();
            records.set(num - 1, task);
        } catch(IndexOutOfBoundsException e) {
            throw new DukeException("No task found in that index!");
        }
    }

    /**
     * Delete the task at the index
     * @param num index where the task located at
     * @return task that being deleted
     * @throws DukeException when no task found in that index
     */
    public Task deleteTask(int num) throws DukeException{
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
     * Get the listing in database
     * @return listing in database
     */
    public List<Task> getListing() {
        return records;
    }

    /**
     * Get Task at specific index
     * @param num index the task locate at
     * @return Task at the index
     */
    public Task getTask(int num) {
        return records.get(num - 1);
    }

    /**
     * Get the total amount of task in record
     * @return the amount of task in record
     */
    public int getAmountOfTask() {
        return records.size();
    }
}
