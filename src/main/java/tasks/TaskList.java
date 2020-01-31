package tasks;

import processor.DukeProcessor;
import processor.Storage;
import processor.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to handle the list of tasks as entered by the user. Has methods to modify the list of tasks.
 */
public class TaskList {

    List<Task> taskList;
    DukeProcessor processor;

    /**
     * Constructor of a TaskList.
     *
     * @param processor Duke's processor.
     */
    public TaskList(DukeProcessor processor) {
        this.processor = processor;
        taskList = new ArrayList<Task>();

        init();
    }

    /**
     * Initiates the TaskList. Attempts to load the tasks from a external .txt file using Storage class.
     */
    private void init() {
        try {
            Storage.init();
            taskList = Storage.loadTasks(processor);
        } catch (Exception e) {
            e.printStackTrace();
            Ui.print("It looks like you have no previously saved tasks! Starting a new list for you...");
        }
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added to the list.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Removes a task from the task list.
     *
     * @param task Task to be removed.
     * @return Boolean indicating whether the task was successfully removed or not. True if removed.
     */
    public boolean remove(Task task) {
        return taskList.remove(task);
    }

    /**
     * Removes the task from the task list at that index.
     *
     * @param index Index of task to be removed.
     * @return Task that was removed from the list at that index.
     */
    public Task removeAt(int index) {
        return taskList.remove(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return Size of the task list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Gets the task stored in the task list at that index.
     *
     * @param index Index of task to get.
     * @return Task at the chosen index.
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Prints the tasks in the order in which they were in the list.
     */
    public void printTasks() {
        if (taskList.size() == 0) {
            Ui.print("Looks like you don't have any tasks entered! Try entering one with the "
                    + "commands 'todo', 'deadline' or 'event'!");
        } else {
            Ui.print("Here are the " + taskList.size() + " tasks I've noted down for you:");
            for (int i = 0; i < taskList.size(); i++) {
                Ui.print(String.format("%d. %s", i + 1, taskList.get(i)));
            }
        }
    }

    /**
     * Prints the tasks that start on the selected searchDate.
     *
     * @param searchDate Date to filter tasks.
     */
    public void printTasksOn(LocalDate searchDate) {
        List<Task> outputTaskList = new ArrayList<Task>();
        for (Task task : taskList) {
            if (task instanceof DeadlineTask) {
                if (((DeadlineTask) task).getParsedDeadline().toLocalDate().equals(searchDate)) {
                    outputTaskList.add(task);
                }
            } else if (task instanceof EventTask) {
                if (((EventTask) task).getParsedStartTime().toLocalDate().equals(searchDate)) {
                    outputTaskList.add(task);
                }
            }
        }

        if (outputTaskList.size() == 0) {
            Ui.print("Looks like you don't have any tasks entered on this date! Try entering one with "
                    + "the commands 'todo', 'deadline' or 'event'!");
        } else {
            Ui.print(String.format("Here are the %d tasks I've noted down for you on %s:",
                    outputTaskList.size(), searchDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))));
            for (int i = 0; i < outputTaskList.size(); i++) {
                Ui.print(String.format("%d. %s", i + 1, outputTaskList.get(i)));
            }
        }
    }

    /**
     * Prints the tasks that contain the search String.
     *
     * @param searchText Text to filter the tasks by.
     */
    public void printTasksContaining(String searchText) {
        List<Task> filteredTaskList = new ArrayList<Task>();

        for (Task task : taskList) {
            if (task.getDescription().contains(searchText)) {
                filteredTaskList.add(task);
            }
        }

        for (Task task : filteredTaskList) {
            Ui.print(task.toString());
        }
    }

}
