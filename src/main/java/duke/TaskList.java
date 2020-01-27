package duke;

import duke.task.Task;

import java.util.List;
import java.util.ArrayList;

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
