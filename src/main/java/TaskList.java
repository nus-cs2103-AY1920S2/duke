import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks, that can add or delete tasks.
 */
public class TaskList {
    private List<Task> tasks;

    private TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds the given task to the list.
     *
     * @param task the task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task specified by the index.
     *
     * @param index the index of the task to be deleted
     * @return the deleted task
     */
    public Task deleteTask(int index) {
        // check whether the index is within bounds
        assert index >= 0;
        assert index < tasks.size();

        return tasks.remove(index);
    }

    /**
     * Completes the task specified by the index
     *
     * @param index the index of the task to be completed
     * @return the completed task
     */
    public Task completeTask(int index) {
        // check whether the index is within bounds
        assert index >= 0;
        assert index < tasks.size();

        Task task = tasks.get(index);
        task.tick();
        return task;
    }

    /**
     * Completes the task specified by the index.
     *
     * @param index the index of the task to be completed
     * @param time the new time
     * @return the completed task
     *
     * @throws InvalidClassException when the task is not snoozeable
     */
    public Task snoozeTask(int index, String time) throws InvalidClassException {
        // check whether the index is within bounds
        assert index >= 0;
        assert index < tasks.size();

        Task task = tasks.get(index);
        if (task.isSnoozeable()) {
            Task snoozedTask = task.snooze(time);
            tasks.set(index, snoozedTask);
            return snoozedTask;
        } else {
            throw new InvalidClassException("Task not snoozeable");
        }
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Returns the task at the given index.
     *
     * @param index the index of the task to be retrieved
     * @return the task at the index
     */
    public Task getTask(int index) {
        // check whether index is within bounds
        assert index >= 0;
        assert index < tasks.size();

        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the number of tasks in the list
     */
    public int getCount() {
        return tasks.size();
    }

    /**
     * Returns a new list containing the tasks with the keyword in description.
     *
     * @param keyword the keyword to search for
     * @return a new TaskList of matching tasks
     */
    public TaskList getRelevantTasks(String keyword) {
        List<Task> relevantTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.description.contains(keyword)) {
                relevantTasks.add(task);
            }
        }

        return new TaskList(relevantTasks);
    }

    @Override
    public String toString() {
        String result = "";

        for (int i = 0; i < tasks.size(); i++) {
            result += (i + 1) + "."
                    + tasks.get(i) + "\n";
        }

        return result;
    }
}
