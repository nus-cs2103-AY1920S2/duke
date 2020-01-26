import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

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
     * Adds a to-do task to the list.
     *
     * @param description Description of to-do task.
     */
    public void addToDo(String description) {
        tasks.add(new ToDo(description, 'T'));
    }

    /**
     * Adds an event to the list.
     *
     * @param description Description of event.
     * @param date Date of event.
     * @param time Time of event.
     */
    public void addEvent(String description, LocalDate date, LocalTime time) {
        tasks.add(new Event(description, 'E', date, time));
    }

    /**
     * Adds a deadline task to the list.
     *
     * @param description Description of deadline task.
     * @param date Date of deadline of task.
     * @param time Time of deadline of task.
     */
    public void addDeadline(String description, LocalDate date, LocalTime time) {
        tasks.add(new Deadline(description, 'D', date, time));
    }
}
