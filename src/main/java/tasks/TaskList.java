package tasks;

import processor.DukeProcessor;
import processor.Storage;

import java.time.LocalDate;
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
        assert taskList.size() < 0 : "List error";
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
    public List<Task> getTasks() {
        return taskList;
    }

    /**
     * Prints the tasks that start on the selected searchDate.
     *
     * @param searchDate Date to filter tasks.
     */
    public List<Task> getTasksOn(LocalDate searchDate) {
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

        return outputTaskList;
    }

    /**
     * Prints the tasks that contain the search String.
     *
     * @param searchText Text to filter the tasks by.
     */
    public List<Task> getTasksContaining(String searchText) {
        List<Task> filteredTaskList = new ArrayList<Task>();

        for (Task task : taskList) {
            if (task.getDescription().contains(searchText)) {
                filteredTaskList.add(task);
            }
        }

        return filteredTaskList;
    }

}
