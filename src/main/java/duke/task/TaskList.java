package duke.task;

import duke.exception.InvalidCommandException;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks = new ArrayList<>();

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void setAsDone(int index) throws InvalidCommandException {
        //check if index is valid
        if (index > tasks.size() || index <= 0) {
            throw new InvalidCommandException("     ☹ OOPS!!! I cannot set a "
                    + "non-existent task to be done.");
        }

        //mark selected task to be done
        tasks.get(index - 1).markDone();
    }

    public Task deleteTask(int index) throws InvalidCommandException {
        //check if index is valid
        if (index > tasks.size() || index <= 0) {
            throw new InvalidCommandException("     ☹ OOPS!!! I cannot delete a "
                    + "non-existent task.");
        }

        //delete the task from the list and return it
        Task taskToDelete = tasks.remove(index - 1);
        return taskToDelete;
    }

    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    public List<Task> getList() {
        return tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    @Override
    public String toString() {
        String listRepresentation = "";
        for (int i = 0; i < tasks.size(); i++) {
            listRepresentation += String.format("     %d. %s\n", (i + 1), tasks.get(i));
        }
        return listRepresentation;
    }
}
