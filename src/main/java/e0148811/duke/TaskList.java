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

    public void assignPriorityToTask(int index, PriorityLevel level) {
        assert index >= 0 && index < list.size() : "index is out of bound";
        Task task = list.get(index);
        switch (level) {
        case HIGH:
            task.setHighPriority();
            break;
        case TOP:
            task.setTopPriority();
            break;
        case NORMAL:
            task.setNormalPriority();
            break;
        }
    }

    public void printList() {
        System.out.println("Here is the task list:");
        for (int i = 1; i <= list.size(); i++) {
            System.out.println(i + ". " + list.get(i - 1));
        }
        System.out.println(getNumOfTasksInString());
    }

    public List<Task> getList() {
        return list;
    }

    public String getNumOfTasksInString() {
        return "Currently there is/are " + list.size() + " task(s) in the list.";
    }

    public void addTaskToList(Task task) {
        list.add(task);
        System.out.println("Noted, the following task is stored:");
        System.out.println(task);
        System.out.println(getNumOfTasksInString());
    }

    public void markATaskDone(int index) {
        assert index < list.size() : "index is out of bound";
        Task taskToBeCompleted = list.get(index);
        taskToBeCompleted.setDone();
        System.out.println("Noted, the following task is marked done:");
        System.out.println(taskToBeCompleted);
    }

    public void deleteATask(int index) {
        assert index < list.size() : "index is out of bound";
        Task t = list.remove(index);
        System.out.println("Noted, the following task is removed from the list:");
        System.out.println(t);
        System.out.println(getNumOfTasksInString());
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

    public void showTaskPrioritised(int index) {
        System.out.println("Noted. The priority level of the following task has been set:");
        System.out.println(list.get(index));
    }
}
