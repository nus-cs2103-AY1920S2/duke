package duke.task;

import duke.exception.DukeException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getByIndex(int index) throws DukeException {
        try {
            return tasks.get(index - 1);
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("☹ OOPS!!! We don't have this number in list!!!");
        }
    }

    public int getSize() {
        return tasks.size();
    }

    public ArrayList<Task> getList() {
        return tasks;
    }

    public Task deleteTaskByIndex(int index) throws DukeException{
        try {
            return tasks.remove(index - 1);
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("☹ OOPS!!! We don't have this number in list!!!");
        }
    }
}
