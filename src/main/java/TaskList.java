import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

/**
 * Represents a list of tasks, that can add or delete tasks.
 */
public class TaskList {
    private List<Task> tasks;

    protected TaskList(List<Task> tasks) {
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
     *
     * @throws IndexOutOfBoundsException when index is invalid
     */
    public Task deleteTask(int index) throws IndexOutOfBoundsException {
        return tasks.remove(index);
    }

    /**
     * Completes the task specified by the index.
     *
     * @param index the index of the task to be completed
     * @return the completed task
     *
     * @throws IndexOutOfBoundsException when index is invalid
     */
    public Task completeTask(int index) throws IndexOutOfBoundsException {
        Task task = tasks.get(index);
        task.tick();
        return task;
    }

    /**
     * Snoozes the task specified by the index.
     *
     * @param index the index of the task to be completed
     * @param time the new time
     * @return the completed task
     *
     * @throws InvalidClassException when the task is not snoozeable
     */
    public Task snoozeTask(int index, String time) throws
            InvalidClassException,
            InvalidPropertiesFormatException,
            IndexOutOfBoundsException {
        // check whether the index is within bounds
        assert index >= 0 && index < tasks.size();

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
     *
     * @throws IndexOutOfBoundsException when index is invalid
     */
    public Task getTask(int index) throws IndexOutOfBoundsException {
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
