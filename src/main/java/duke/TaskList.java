package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList containing the elements of the specified list of tasks.
     * @param tasks The list whose elements are to be placed into this TaskList.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the task at the specified position in this TaskList.
     * @param index The 1-based index of the task to return.
     * @return The task at the specified position in the TaskList.
     * @throws DukeException If the index is out of range (index < 1 || index > size()).
     */
    public Task get(int index) throws DukeException {
        try {
            return tasks.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task cannot be found.");
        }
    }

    /**
     * Appends the specified task to the end of this TaskList.
     * @param task Task to be appended to this TaskList.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the task at the specified position in this TaskList.
     * Shifts any subsequent tasks to the left (subtracts one from their indices).
     * @param index The 1-based index of the task to be removed.
     * @return The task that was removed from the TaskList.
     * @throws DukeException If the index is out of range (index < 1 || index > size()).
     */
    public Task remove(int index) throws DukeException {
        try {
            return tasks.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task cannot be found.");
        }
    }

    /**
     * Returns a TaskList containing tasks on the specified date.
     * @param date The date to to filter.
     * @return A TaskList containing tasks on the specified date.
     */
    public TaskList find(LocalDate date) {
        TaskList filteredTasks = new TaskList();
        for (Task task : tasks) {
            if ((task instanceof Deadline && ((Deadline)task).getDate().equals(date))
                    || (task instanceof Event && ((Event)task).getDate().equals(date))) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }

    /**
     * Returns a TaskList containing tasks with the specified keyword.
     * @param keyword The keyword to search for.
     * @return A TaskList containing tasks with the specified keyword.
     */
    public TaskList find(String keyword) {
        TaskList filteredTasks = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }

    /**
     * Returns a list of all tasks in this TaskList.
     * @return A list of all tasks in the TaskList.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns true if this TaskList contains no tasks.
     * @return true if this TaskList contains no tasks.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the number of tasks in this TaskList.
     * @return The number of tasks in the TaskList.
     */
    public int size() {
        return tasks.size();
    }
}
