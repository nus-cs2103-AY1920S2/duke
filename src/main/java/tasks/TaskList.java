package tasks;

import commons.StringUtil;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * TaskList stores a list of tasks and contains operations to add, retrieve
 * and delete tasks.
 */
public class TaskList {
    public ArrayList<Task> list;


    /**
     * Initializes the task list.
     */
    public TaskList(ArrayList<Task> taskList) {
        list = taskList;
    }

    /**
     * Retrieves this task list.
     *
     * @return this task list.
     */
    public ArrayList<Task> getTaskList() {
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
     */
    public void deleteTask(int taskNumber) {
        list.remove(taskNumber);
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
        list.get(taskNumber).setDone();
    }

    /**
     * Returns specified task number.
     *
     * @param taskNumber of the task requested.
     * @return task with the corresponding task number.
     */
    public Task getTask(int taskNumber) {
        return list.get(taskNumber);
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
     * Prints all tasks in this task list containing the keyword in a numbered order.
     *
     * @return the string of all tasks containing the keyword in this task list.
     */
    public TaskList findTaskContainingKeyword(String keyword) {
        ArrayList<Task> filteredByKeyword = (ArrayList<Task>) list.stream()
                .filter(task -> StringUtil.containsWordIgnoreCase(task.getName().toString(), keyword))
                .collect(Collectors.toList());
        return new TaskList(filteredByKeyword);
    }

    public TaskList findTaskContainingTag(String tag) {
        ArrayList<Task> filteredByTag = (ArrayList<Task>) list.stream()
                .filter(task -> task.checkTags(tag))
                .collect(Collectors.toList());
        return new TaskList(filteredByTag);
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

    public void setTaskList(ArrayList<Task> taskList) {
        this.list = taskList;
    }
}
