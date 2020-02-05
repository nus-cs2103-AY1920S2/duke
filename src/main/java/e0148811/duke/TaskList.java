package e0148811.duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public TaskList(List<Task> list) {
        this.list = list;
    }

    public void printList() {
        System.out.println("Here is the task list:");
        for (int i = 1; i <= list.size(); i++) {
            System.out.println(i + ". " + list.get(i - 1));
        }
        System.out.println(getNumOfTasks());
    }

    public List<Task> getList() {
        return list;
    }

    public String getNumOfTasks() {
        return "Currently there is/are " + list.size() + " task(s) in the list.";
    }

    public void addTaskToList(Task task) {
        list.add(task);
        System.out.println("Noted, the following task is stored:");
        System.out.println(task);
        System.out.println(getNumOfTasks());
    }

    public void markATaskDone(int index) {
        Task taskToBeCompleted = list.get(index);
        taskToBeCompleted.setDone();
        System.out.println("Noted, the following task is marked done:");
        System.out.println(taskToBeCompleted);
    }

    public void deleteATask(int index) {
        Task t = list.remove(index);
        System.out.println("Noted, the following task is removed from the list:");
        System.out.println(t);
        System.out.println(getNumOfTasks());
    }

    public Task getTask(int index) {
        return list.get(index);
    }

    public void clearTheList() {
        list = new ArrayList<>();
    }

    public void cleanTheList() {
        List<Task> newList = new ArrayList<>();
        for (Task t : list) {
            if (!t.isDone()) {
                newList.add(t);
            }
        }
        list = newList;
        System.out.println("List cleared.");
        printList();
    }
}
