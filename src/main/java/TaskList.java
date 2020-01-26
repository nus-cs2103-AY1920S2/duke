package main.java;

import java.util.ArrayList;

public class TaskList {

    public ArrayList<Task> taskList = new ArrayList<>();

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public int getSize() {
        return taskList.size();
    }

    public void addTask (Task task) {
        taskList.add(task);
    }

    public void deleteTask (int index) {
        taskList.remove(index);
    }

    public void setDone (int index) {
        taskList.get(index).markAsDone();
    }

    public Task getTask (int index) {
        return taskList.get(index);
    }

    public void printList() {
        System.out.println("These items are in your list: ");
        for (int i = 0; i < taskList.size(); i++){
            System.out.println(i + 1 + ". " + taskList.get(i));
        }
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }
}

