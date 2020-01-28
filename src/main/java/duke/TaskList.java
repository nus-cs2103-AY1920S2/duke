package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Task get(int taskId) throws DukeException {
        try {
            return tasks.get(taskId - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task cannot be found.");
        }
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task remove(int taskId) throws DukeException {
        try {
            return tasks.remove(taskId - 1);
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

    public List<Task> getTasks() {
        return tasks;
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int size() {
        return tasks.size();
    }
}
