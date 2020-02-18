package e0148811.duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static int ONE_TO_CONVERT_BETWEEN_1_BASED_AND_0_BASED_INDEX = 1;
    private Ui ui;
    List<Task> list;

    public TaskList(Ui ui) {
        this.ui = ui;
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
            System.out.println(i + ". " + list.get(i - ONE_TO_CONVERT_BETWEEN_1_BASED_AND_0_BASED_INDEX));
        }
        System.out.println(getTotalNumOfTasks());
    }

    public void printListBasedOnPriority(PriorityLevel level) throws DukeException {
        System.out.println("Here is the task list of the given priority level:");
        int count = 0;
        for (int i = 1; i <= list.size(); i++) {
            Task task = list.get(i - ONE_TO_CONVERT_BETWEEN_1_BASED_AND_0_BASED_INDEX);
            if (task.getPriorityLevel().equals(level)) {
                System.out.println(i + ". " + task);
                count++;
            }
        }
        System.out.println(getNumOfSelectedTasks(count));
        System.out.println(getTotalNumOfTasks());
    }

    private String getNumOfSelectedTasks(int count) {
        return count+ " task(s) belong to the specified priority level.";
    }

    public List<Task> getList() {
        return list;
    }

    public String getTotalNumOfTasks() {
        return "Currently there is/are " + list.size() + " task(s) in the whole task list.";
    }

    public void addTaskToList(Task task) {
        list.add(task);
        System.out.println("Noted, the following task is stored:");
        System.out.println(task);
        System.out.println(getTotalNumOfTasks());
    }

    public void markATaskDone(int index) {
        assert index < list.size() && index >= 0: "index is out of bound";
        Task taskToBeCompleted = list.get(index);
        if (taskToBeCompleted.isDone()) {
            System.out.println("The specified task is already marked done:");
        } else {
            taskToBeCompleted.setDone();
            System.out.println("Noted, the following task is marked done:");
        }
        System.out.println((index + ONE_TO_CONVERT_BETWEEN_1_BASED_AND_0_BASED_INDEX)
                + ". " + taskToBeCompleted);
    }

    public void removeATask(int index) {
        assert index < list.size() : "index is out of bound";
        Task t = list.remove(index);
        System.out.println("Noted, the following task is removed from the list:");
        System.out.println(t);
        System.out.println(getTotalNumOfTasks());
    }

    public Task getTask(int index) {
        return list.get(index);
    }

    public void removeAllTasks() {
        list = new ArrayList<>();
    }

    public void removeCompletedTasks() {
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
