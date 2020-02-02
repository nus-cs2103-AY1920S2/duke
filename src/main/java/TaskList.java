import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {

    public ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructor for TaskList.
     *
     * @param tasks ArrayList for TaskList.
     */
    public TaskList(ArrayList tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds task to TaskList.
     *
     * @param task Task to be added to TaskList.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Get number of tasks in TaskList.
     *
     * @return Number of tasks in TaskList.
     */
    public int getTaskListSize() {
        return tasks.size();
    }

    /**
     * Get specified task in TaskList.
     *
     * @param taskNumber Task number in TaskList .
     * @return Task specified.
     */
    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber);
    }

    /**
     * Deletes specified task in TaskList.
     *
     * @param taskNumber Task number in TaskList.
     */
    public void deleteTask(int taskNumber) {
        tasks.remove(taskNumber);
    }
}
