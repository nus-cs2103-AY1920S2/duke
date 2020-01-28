package task;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public int getSize() {
        return tasks.size();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int id) {
        return tasks.get(id);
    }

    public void delete(int id) {
        tasks.remove(id);
    }

}