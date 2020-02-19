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

    public String printList() {
        StringBuilder output = new StringBuilder("Here is the task list:\n");
        for (int i = 1; i <= list.size(); i++) {
            output.append(i).append(". ")
                    .append(list.get(i - ONE_TO_CONVERT_BETWEEN_1_BASED_AND_0_BASED_INDEX)).append("\n");
        }
        output.append(getTotalNumOfTasks());
        return output.toString();
    }

    public String printListBasedOnPriority(PriorityLevel level) throws DukeException {
        StringBuilder output = new StringBuilder("Here is the task list of the given priority level:");
        int count = 0;
        for (int i = 1; i <= list.size(); i++) {
            Task task = list.get(i - ONE_TO_CONVERT_BETWEEN_1_BASED_AND_0_BASED_INDEX);
            if (task.getPriorityLevel().equals(level)) {
                output.append(i).append(". ").append(task);
                count++;
            }
        }
        output.append(getNumOfSelectedTasks(count));
        output.append(getTotalNumOfTasks());
        return output.toString();
    }

    private String getNumOfSelectedTasks(int count) {
        return count + " task(s) belong to the specified priority level.";
    }

    public List<Task> getList() {
        return list;
    }

    public String getTotalNumOfTasks() {
        return "Currently there is/are " + list.size() + " task(s) in the whole task list.\n";
    }

    public String addTaskToList(Task task) {
        list.add(task);
        String output = "Noted, the following task is stored:\n";
        output += task;
        output += "\n";
        output += getTotalNumOfTasks();
        return output;
    }

    public String markATaskDone(int index) {
        assert index < list.size() && index >= 0 : "index is out of bound";
        Task taskToBeCompleted = list.get(index);
        String output = "";
        if (taskToBeCompleted.isDone()) {
            output += "The specified task is already marked done:\n";
        } else {
            taskToBeCompleted.setDone();
            output += "Noted, the following task is marked done:\n";
        }
        output += ((index + ONE_TO_CONVERT_BETWEEN_1_BASED_AND_0_BASED_INDEX)
                + ". " + taskToBeCompleted);
        return output;
    }

    public String removeATask(int index) {
        assert index < list.size() : "index is out of bound";
        Task t = list.remove(index);
        String output = "Noted, the following task is removed from the list:\n";
        output += t;
        output += "\n";
        output += getTotalNumOfTasks();
        return output;
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

    public String showTaskPrioritised(int index) {
        String output = "Noted. The priority level of the following task has been set:\n";
        output += list.get(index);
        return output;
    }
}
