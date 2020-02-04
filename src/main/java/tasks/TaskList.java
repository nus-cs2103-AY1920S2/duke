package tasks;

import command.DukeException;

import java.util.ArrayList;

/**
 * TaskList stores a list of tasks and contains operations to add, retrieve
 * and delete tasks.
 */
public class TaskList {
    public static ArrayList<Task> list;

    /**
     * Initialize the task list.
     */
    public static void initializeArray(ArrayList<Task> currentList) {
        list = currentList;
    }

    /**
     * Retrieve this task list.
     * @return this task list.
     */
    public static ArrayList<Task> getList() {
        return list;
    }

    /**
     * Add task to this task list.
     * @param taskToAdd task to add to the list.
     */
    public static void addTask(Task taskToAdd) {
        list.add(taskToAdd);
    }

    /**
     * Delete task with the specified task number from this task list.
     * @param taskNumber task to be deleted.
     * @return deleted task.
     */
    public static Task deleteTask(int taskNumber) {
        if (taskNumber > list.size()) {
            throw new DukeException("☹ OOPS!!! There is no such task.");
        }
        Task deletedTask = list.get(taskNumber - 1);
        list.remove(taskNumber - 1);
        return deletedTask;
    }

    /**
     * Clear all tasks from this task list.
     */
    public static void clearAll() {
        list.clear();
    }

    /**
     * Mark task with specified task number as done.
     * @param taskNumber task to be marked as done.
     * @return task marked as done.
     */
    public static Task markAsDone(int taskNumber) {
        if (taskNumber > list.size()) {
            throw new DukeException("☹ OOPS!!! There is no such task.");
        }
        list.get(taskNumber - 1).setDone();
        return list.get(taskNumber - 1);
    }

    /**
     * Return total number of tasks in this task list.
     * @return total number of tasks in this task list.
     */
    public static int getTotalTasks() {
        return list.size();
    }

    /**
     * Checks if this task list is empty.
     * @return true if task list is empty and false otherwise.
     */
    public static boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Return information on the number of tasks in the list.
     * @return the string of the total number of tasks in the list.
     */
    public static String printTotalTasks() {
        if (getTotalTasks() == 0) {
            return "\tNow you have no tasks in the list.";
        } else if (getTotalTasks() == 1) {
            return String.format("\tNow you have %d task in the list.", getTotalTasks());
        } else {
            return String.format("\tNow you have %d tasks in the list.", getTotalTasks());
        }
    }

    /**
     * Print all tasks in this task list containing the keyword in a numbered order.
     * @return the string of all tasks containing the keyword in this task list.
     */
    public static String findTaskContainingKeyword(String keyword) {
        String printedList = "";
        int taskNumber = 1;
        for (Task task : list) {
            if (task.getDescription().contains(keyword)) {
                printedList = printedList + "\n\t\t" + taskNumber + ". \t" + task;
                taskNumber++;
            }
        }
        return printedList;
    }
    /**
     * Print all tasks in this task list in a numbered order.
     * @return the string of all tasks in this task list.
     */
    public static String printList() {
        String printedList = "";
        int taskNumber = 1;
        for (Task task : list) {
            printedList = printedList + "\n\t\t" + taskNumber + ". \t" + task;
            taskNumber++;
        }
        return printedList;
    }
}
