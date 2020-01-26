package main.duke.data;

import main.duke.tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public int getSize() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(int idx) {
        return tasks.get(idx);
    }

    public void removeTask(int idx) {
        tasks.remove(idx);
    }

    public ArrayList<Task> getList() {
        return tasks;
    }

    public void printTask() {
        for (Task task: tasks) {
            System.out.println(task.getId() + "." + task);
        }
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }
}
