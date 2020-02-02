import Task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void markDone(String str) throws IllegalArgumentException {
        int num = Integer.parseInt(str.substring(5));
        Parser parser = new Parser();
        Ui ui = new Ui();
        if (!parser.inRange(num, this.tasks.size())) {
            throw new IllegalArgumentException("OOPS!!! The number you checked for may not be valid.");
        }
        this.tasks.get(num - 1).markAsDone();
        ui.doneMessage(num, this.tasks);
    }

    public void addTask(Task t) {
        Ui ui = new Ui();
        tasks.add(t);
        ui.addMessage(t, tasks.size());
    }

    public void delete(String str) throws IllegalArgumentException {
        Parser parser = new Parser();
        Ui ui = new Ui();
        int num = Integer.parseInt(str.substring(7));
        if (!parser.inRange(num, this.tasks.size())) {
            throw new IllegalArgumentException("OOPS!!! The number you checked for may not be valid.");
        }
        Task t = tasks.remove(num - 1);
        ui.deleteMessage(num, t, this.tasks.size());
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
