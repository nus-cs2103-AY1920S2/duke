import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Contains the task list.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Class constructor used when file to load from cannot be found.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Class constructor.
     *
     * @param tasks List of tasks that were previously saved in duke.txt.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Number of tasks in list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns a particular task.
     *
     * @param taskIndex Index of task to be returned.
     * @return Task at index taskIndex.
     */
    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex);
    }

    /**
     * Remove a task from task list.
     *
     * @param taskIndex Index of task to be removed from list.
     */
    public void deleteTask(int taskIndex) {
        tasks.remove(taskIndex);
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Returns a list of tasks that contains keyword.
     *
     * @param keyword Keyword to be found in the description.
     * @return List of tasks that contains keyword.
     */
    public ArrayList<Task> findTargets(String keyword) {
        List<Task> targets = tasks.stream().filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toList());

        ArrayList<Task> matchedTasks = new ArrayList<Task>(targets);

        return matchedTasks;
    }
}
