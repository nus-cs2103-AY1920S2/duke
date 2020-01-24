package duke;

import java.util.ArrayList;
import exception.DukeException;
import task.Task;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int size() {
        return this.tasks.size();
    }

    public Boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    public ArrayList<Task> getAllTask() {
        return this.tasks;
    }

    public String getTask(int index) {
        return this.tasks.get(index).toString();
    }

    public Task popTask(int index) {
        return this.tasks.remove(index);
    }

    public Task addTask(String input) throws DukeException {
        Task newTask = Task.newTask(input);
        this.tasks.add(newTask);
        return newTask;
    }

    public void markDone(int index) {
        Task currTask = this.tasks.get(index);
        currTask.setDone();
    }

    public String[] allTasks() {
        String[] strings = new String[size()];
        for (int i = 0; i < size(); i++) {
            strings[i] = String.format("%d.%s", i + 1, this.tasks.get(i));
        }
        return strings;
    }
}
