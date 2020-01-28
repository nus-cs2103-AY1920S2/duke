package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task removeTask(int index) throws DukeException{
        try {
            return tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! Index is out of bounds.");
        }
    }

    public Task getTask(int index) throws DukeException {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! Index is out of bounds.");
        }
    }

    public int getSize() {
        return tasks.size();
    }

    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> filtered = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.getDescription().contains(keyword)) {
                filtered.add(task);
            }
        }
        return filtered;
    }
}
