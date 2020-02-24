package liaomeng.duke;

import java.util.ArrayList;
import java.util.List;

/**
 * The class that represents the task list.
 */
public class TaskList {
    private static int ONE_TO_CONVERT_BETWEEN_1_BASED_AND_0_BASED_INDEX = 1;
    List<Task> list;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Creates a task list that already contains some tasks.
     *
     * @param list the list of Tasks
     */
    public TaskList(List<Task> list) {
        this.list = list;
    }

    /**
     * Changes the priority level of a task in the list to a specified level.
     *
     * @param index index of the task, whose priority level will be assigned, in the task list.
     * @param level priority level specified.
     * @return String representation of assignment acknowledgement and the task whose priority level was assigned.
     */
    public String assignPriorityToTask(int index, PriorityLevel level) {
        assert index >= 0 && index < list.size() : "index is out of bound";
        Task task = list.get(index);
        task.setPriority(level);
        String output = "Noted, the priority level of the following task has been set:\n";
        output += task;
        return output;
    }

    /**
     * Returns a String representation of all the tasks in the task list.
     */
    public String printList() {
        StringBuilder output = new StringBuilder("Here is the task list:\n");
        for (int i = 1; i <= list.size(); i++) {
            output.append(i).append(") ")
                    .append(list.get(i - ONE_TO_CONVERT_BETWEEN_1_BASED_AND_0_BASED_INDEX)).append("\n");
        }
        output.append(getTotalNumOfTasks());
        return output.toString();
    }

    /**
     * Returns a String representation of all tasks with a specified priority level in the task list.
     *
     * @param level priority level specified.
     */
    public String printListBasedOnPriority(PriorityLevel level) {
        StringBuilder output = new StringBuilder("Here is the list of tasks "
                + "with your specified priority level:\n");
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

    /**
     * Returns a String reporting the number of tasks in the task list.
     */
    String getTotalNumOfTasks() {
        return "Currently there is/are " + list.size() + " task(s) in the task list.\n";
    }

    /**
     * Adds a task into the list.
     *
     * @param task task to be added.
     * @return String representation of the added task.
     */
    public String addTaskToList(Task task) {
        list.add(task);
        String output = "Noted, the following task is stored:\n";
        output += task;
        output += "\n";
        output += getTotalNumOfTasks();
        return output;
    }

    /**
     * Attempts to set a task as Done.
     *
     * @param index index of the task in the task list.
     * @return String representation of setting result and the task the user want to mark as done.
     */
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

    /**
     * Removes a task from the task list.
     *
     * @param index index of the task in the task list.
     * @return String representation of the task removed.
     */
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

    /**
     * Clear the task list.
     */
    public void removeAllTasks() {
        list = new ArrayList<>();
    }

    /**
     * Removes all tasks that are marked as Done in the task list.
     *
     * @return String representation of all the remaining tasks in the list.
     */
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
}
