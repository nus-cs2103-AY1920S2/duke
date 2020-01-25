package dukebot.tasklist;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public int size() {
        return this.taskList.size();
    }

    public Task getTask(int taskInd) {
        return this.taskList.get(taskInd);
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public Task deleteTask(int taskInd) {
        if (taskInd >= size() || taskInd < 0) {
            return null;
        } else {
            Task task = this.taskList.get(taskInd);
            this.taskList.remove(taskInd);
            return task;
        }
    }

}