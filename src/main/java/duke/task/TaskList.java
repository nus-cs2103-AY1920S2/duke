package duke.task;

import duke.exception.InvalidCommandException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for the task list.
 * Contains operations for the task list.
 */
public class TaskList {
    private static final String DONE_NO_SUCH_TASK_MESSAGE = "HEY!!! I cannot set a non-existent task to be done.";
    private static final String DELETE_NO_SUCH_TASK_MESSAGE = "HEY!!! I cannot destroy a non-existent task.";

    /** List of tasks. */
    private List<Task> tasks = new ArrayList<>();

    /**
     * Constructs a new task list given a list of tasks.
     *
     * @param tasks a list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Sets a task in the list to be done based on the index.
     *
     * @param index the index of the task based on the list's string representation to be set as done.
     * @throws InvalidCommandException if the index given is out of bounds
     */
    public void setAsDone(int index) throws InvalidCommandException {
        if (index > tasks.size() || index <= 0) {
            throw new InvalidCommandException(DONE_NO_SUCH_TASK_MESSAGE);
        }
        assert tasks.size() > 0 : "task list should not be empty";

        tasks.get(index - 1).markDone();
    }

    /**
     * Deletes a task from the list based on the index and returns the task deleted.
     *
     * @param index the index of the task based on the list's string representation to be deleted.
     * @return the deleted task.
     * @throws InvalidCommandException if the index given is out of bounds.
     */
    public Task deleteTask(int index) throws InvalidCommandException {
        if (index > tasks.size() || index <= 0) {
            throw new InvalidCommandException(DELETE_NO_SUCH_TASK_MESSAGE);
        }
        assert tasks.size() > 0 : "task list should not be empty";

        Task taskToDelete = tasks.remove(index - 1);
        return taskToDelete;
    }

    /**
     * Gets the task from the list based on the index.
     *
     * @param index the index of the task based on the list's string representation to be retrieved.
     * @return the task of the respective index.
     */
    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    /**
     * Finds the list of tasks that contains the search phrase.
     *
     * @param searchPhrase the desired search phrase to search the list.
     * @return the string representation of the filtered list.
     */
    public String findTasksBySearchPhrase(String searchPhrase) {
        String relevantTasksRepresentation = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(searchPhrase)) {
                relevantTasksRepresentation += String.format("%d. %s\n", (i + 1), tasks.get(i));
            }
        }
        return relevantTasksRepresentation;
    }

    /**
     * Finds the list of all tasks that is expiring up to specified number of days from today.
     * The list is ordered by deadlines first followed by events.
     *
     * @param daysFromToday how many days from today to look at.
     * @return the string representation of the expiring tasks.
     */
    public String findAllExpiringTasks(int daysFromToday) {
        String relevantTasksRepresentation = findExpiringDeadlines(daysFromToday) + findExpiringEvents(daysFromToday);
        return relevantTasksRepresentation;
    }

    /**
     * Finds the list of deadline that is due in the next specified number of days from today.
     *
     * @param daysFromToday how many days from today to look at.
     * @return the string representation of the filtered deadlines.
     */
    public String findExpiringDeadlines(int daysFromToday) {
        LocalDate currentDate = LocalDate.now();
        String relevantTasksRepresentation = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            if (currentTask instanceof Deadline) {
                LocalDate deadlineDate = ((Deadline) currentTask).getByDate();
                if (deadlineDate.isAfter(currentDate.plusDays(daysFromToday))
                        || deadlineDate.isBefore(currentDate)) {
                    continue;
                } else if (deadlineDate.isEqual(currentDate)
                        && ((Deadline) currentTask).getByTime().isBefore(LocalTime.now())) {
                    continue;
                }
                relevantTasksRepresentation += String.format("%d. %s\n", (i + 1), tasks.get(i));
            }
        }
        return relevantTasksRepresentation;
    }

    /**
     * Finds the list of events that is happening in the next specified number of days from today.
     *
     * @param daysFromToday how many days from today to look at.
     * @return the string representation of the filtered events.
     */
    public String findExpiringEvents(int daysFromToday) {
        LocalDate currentDate = LocalDate.now();
        String relevantTasksRepresentation = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            if (tasks.get(i) instanceof Event) {
                LocalDate eventDate = ((Event) currentTask).getAtDate();
                if (eventDate.isAfter(currentDate.plusDays(daysFromToday))
                        || eventDate.isBefore(currentDate)) {
                    continue;
                } else if (eventDate.isEqual(currentDate)
                        && ((Event) currentTask).getAtTime().isBefore(LocalTime.now())) {
                    continue;
                }
                relevantTasksRepresentation += String.format("%d. %s\n", (i + 1), tasks.get(i));
            }
        }
        return relevantTasksRepresentation;
    }

    /**
     * Gets the list of all the tasks.
     *
     * @return the list of tasks.
     */
    public List<Task> getList() {
        return tasks;
    }

    /**
     * Gets the number of task in the list.
     *
     * @return the number of task currently in the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Gets the string representation of the task list.
     *
     * @return the string representation of the task list.
     */
    @Override
    public String toString() {
        String listRepresentation = "";
        for (int i = 0; i < tasks.size(); i++) {
            listRepresentation += String.format("%d. %s\n", (i + 1), tasks.get(i));
        }
        return listRepresentation;
    }
}
