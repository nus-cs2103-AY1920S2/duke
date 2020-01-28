package duke.data;

import duke.tasks.Task;

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
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + "." + tasks.get(i - 1));
        }
    }

    public ArrayList<Task> find(String str) {
        ArrayList<Task> outputList = new ArrayList<>();
        for (Task task: tasks) {
            if (task.getTask().contains(str)) {
                outputList.add(task);
            }
        }
        return outputList;
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }
}
