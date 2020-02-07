package tasks;

import command.DukeException;

import java.util.ArrayList;

/**
 * TaskList stores a list of tasks and contains operations to add, retrieve
 * and delete tasks.
 */
public class TaskList {
    public ArrayList<Task> list;

    /**
     * Initializes the task list.
     */
    public TaskList(ArrayList<Task> currentList) {
        list = currentList;
    }

    /**
     * Retrieves this task list.
     *
     * @return this task list.
     */
    public ArrayList<Task> getList() {
        return list;
    }

    /**
     * Adds task to this task list.
     *
     * @param taskToAdd task to add to the list.
     */
    public void addTask(Task taskToAdd) {
        list.add(taskToAdd);
    }

    /**
     * Deletes task with the specified task number from this task list.
     *
     * @param taskNumber task to be deleted.
     * @return deleted task.
     */
    public void deleteTask(int taskNumber) {
        if (taskNumber > list.size()) {
            throw new DukeException("☹ OOPS!!! There is no such task.");
        }
        list.remove(taskNumber - 1);
    }

    /**
     * Clears all tasks from this task list.
     */
    public void clearAll() {
        list.clear();
    }

    /**
     * Marks task with specified task number as done.
     *
     * @param taskNumber task to be marked as done.
     */
    public void markAsDone(int taskNumber) {
        if (taskNumber > list.size()) {
            throw new DukeException("☹ OOPS!!! There is no such task.");
        }
        list.get(taskNumber - 1).setDone();
    }

    /**
     * Returns specified task number.
     *
     * @param taskNumber of the task requested.
     * @return task with the corresponding task number.
     */
    public Task getTask(int taskNumber) {
        return list.get(taskNumber - 1);
    }

    /**
     * Returns total number of tasks in this task list.
     *
     * @return total number of tasks in this task list.
     */
    public int getTotalTasks() {
        return list.size();
    }

    /**
     * Checks if this task list is empty.
     *
     * @return true if task list is empty and false otherwise.
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Returns information on the number of tasks in the list.
     *
     * @return the string of the total number of tasks in the list.
     */
    public String printTotalTasks() {
        if (getTotalTasks() == 0) {
            return "\tNow you have no tasks in the list.";
        } else if (getTotalTasks() == 1) {
            return String.format("\tNow you have %d task in the list.", getTotalTasks());
        } else {
            return String.format("\tNow you have %d tasks in the list.", getTotalTasks());
        }
    }

    /**
     * Prints all tasks in this task list containing the keyword in a numbered order.
     *
     * @return the string of all tasks containing the keyword in this task list.
     */
    public String findTaskContainingKeyword(String keyword) {
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
     * Prints all tasks in this task list in a numbered order.
     *
     * @return the string of all tasks in this task list.
     */
    public String printList() {
        String printedList = "";
        int taskNumber = 1;
        for (Task task : list) {
            printedList = printedList + "\n\t\t" + taskNumber + ". \t" + task;
            taskNumber++;
        }
        return printedList;
    }
}
