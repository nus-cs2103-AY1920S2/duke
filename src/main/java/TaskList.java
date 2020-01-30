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
     * @param task the task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task specified by the index.
     * @param index the index of the task to be deleted.
     * @return the deleted task
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Completes the task specified by the index.
     * @param index the index of the task to be completed.
     * @return the completed task
     */
    public Task completeTask(int index) {
        Task task = tasks.get(index);
        task.tick();
        return task;
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the list.
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
            result += i + "."
                    + tasks.get(i) + "\n";
        }

        return result;
    }
}
