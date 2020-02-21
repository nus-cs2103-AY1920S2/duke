package e0148811.duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static int ONE_TO_CONVERT_BETWEEN_1_BASED_AND_0_BASED_INDEX = 1;
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
        task.setPriority(level);
    }

    public String printList() {
        StringBuilder output = new StringBuilder("Here is the task list:\n");
        for (int i = 1; i <= list.size(); i++) {
            output.append(i).append(") ")
                    .append(list.get(i - ONE_TO_CONVERT_BETWEEN_1_BASED_AND_0_BASED_INDEX)).append("\n");
        }
        output.append(getTotalNumOfTasks());
        return output.toString();
    }

    public String printListBasedOnPriority(PriorityLevel level) {
        StringBuilder output = new StringBuilder("Here is the list of tasks " +
                "with your specified priority level:\n");
        int count = addAndCountTasksWithGivenPriority(level, output);
        output.append(getNumOfSelectedTasks(count));
        output.append(getTotalNumOfTasks());
        return output.toString();
    }

    private int addAndCountTasksWithGivenPriority(PriorityLevel level, StringBuilder output) {
        int count = 0;
        for (int i = 1; i <= list.size(); i++) {
            Task task = list.get(i - ONE_TO_CONVERT_BETWEEN_1_BASED_AND_0_BASED_INDEX);
            if (task.getPriorityLevel().equals(level)) {
                output.append(i).append(") ").append(task).append("\n");
                count++;
            }
        }
        return count;
    }

    private String getNumOfSelectedTasks(int count) {
        return count + " task(s) belong to this priority level.\n";
    }

    public List<Task> getList() {
        return list;
    }

    String getTotalNumOfTasks() {
        return "Currently there is/are " + list.size() + " task(s) in the task list.\n";
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
            output += "The specified task is already marked as done:\n";
        } else {
            taskToBeCompleted.setDone();
            output += "Noted, the following task is marked as done:\n";
        }
        output += taskToBeCompleted;
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

    public String removeCompletedTasks() {
        List<Task> newList = new ArrayList<>();
        for (Task t : list) {
            if (!t.isDone()) {
                newList.add(t);
            }
        }
        list = newList;
        return printList();
    }

    public String showTaskPrioritised(int index) {
        String output = "Noted, the priority level of the following task has been set:\n";
        output += list.get(index);
        return output;
    }
}
