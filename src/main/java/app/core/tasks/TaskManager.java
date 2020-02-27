package app.core.tasks;

import java.util.List;
import java.util.ArrayList;

import app.util.Date;
import app.core.Messages;
import app.core.StorageManager;
import app.exceptions.StorageFileException;

/**
 * This class stores and handles all the data related to 
 * tasks in Duke.
 */
public class TaskManager {
    private List<Task> taskList;
    private StorageManager storageManager;

    /**
     * Initializes a new task manager. The method will attempt
     * to load from the storage if data is present.
     */
    public TaskManager() {
        this.storageManager = new StorageManager();
        this.taskList = this.storageManager.load();
    }

    /**
     * Adds a todo task in the manager.
     * @param description The description of the task
     * @return The output to be presented in the UI
     */
    public String addTodoTask(String description) throws StorageFileException {
        return this.add(new Task(description));
    }

    /**
     * Adds a deadline task in the manager.
     * @param description The description of the task
     * @param deadline A Date object representing the deadline 
     *     of the task
     * @return The output to be presented in the UI
     */
    public String addDeadlineTask(String description, Date deadline) throws StorageFileException {
        return this.add(new DeadlineTask(description, deadline));
    }

    /**
     * Adds an event task in the manager.
     * @param description The description of the task
     * @param when A Date object representing the date of 
     *     the event
     * @return The output to be presented in the UI
     */
    public String addEventTask(String description, Date when) throws StorageFileException {
        return this.add(new EventTask(description, when));
    }

    private String add(Task task) throws StorageFileException {
        if (this.taskList.contains(task)) {
            return Messages.ADD_DUPLICATED_TASK_MESSAGE;
        }

        this.taskList.add(task);
        this.storageManager.save(this.taskList);
        return String.format(Messages.ADD_TASK_SUCCESS_MESSAGE, task, this.taskList.size());
    }

    /**
     * Sets a task as done.
     * @param index The index of the task in the task manager
     * @return The output to be presented in the UI
     * @throws IndexOutOfBoundsException If the index is out of the bounds
     *     of the task maanger
     */
    public String setTaskDone(int index) throws IndexOutOfBoundsException, StorageFileException {
        Task task = this.taskList.get(index - 1);
        task.setDone();
        this.storageManager.save(this.taskList);

        return String.format(Messages.SET_TASK_DONE_SUCCESS_MESSAGE, task);
    }

    /**
     * Deletes a task from the task manager.
     * @param index The index of the task in the task manager
     * @return The output to be presented in the UI
     * @throws IndexOutOfBoundsException If the index is out of the bounds 
     *     of the task manager
     */
    public String deleteTask(int index) throws IndexOutOfBoundsException, StorageFileException {
        Task task = this.taskList.remove(index - 1);
        this.storageManager.save(this.taskList);

        return String.format(Messages.DELETE_TASK_SUCCESS_MESSAGE, task, this.taskList.size());
    }

    /**
     * Finds a list of tasks that contains a String and returns
     * a String representation of this list of tasks.
     * @param toMatch The string to match in task descriptions
     * @return The string representation of the list of filtered tasks
     *     that matches the input string
     */
    public String findMatchingTasks(String toMatch) {
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : this.taskList) {
            if (task.getDescription().contains(toMatch)) {
                filteredTasks.add(task);
            }
        }

        if (filteredTasks.size() == 0) {
            return Messages.FILTER_TASK_NO_TASKS_MESSAGE;
        }

        StringBuilder sb = new StringBuilder("Here are the matching tasks: \n");
        for (int i = 0; i < filteredTasks.size(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, filteredTasks.get(i)));
        }
        return sb.toString();
    }

    /**
     * Returns a String representation of the list of tasks.
     * @return The string representation of the list of tasks
     */
    @Override
    public String toString() {
        if (this.taskList.size() == 0) {
            return "You have no tasks";
        }

        StringBuilder sb = new StringBuilder("These are your tasks: \n");
        for (int i = 0; i < this.taskList.size(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, this.taskList.get(i)));
        }
        return sb.toString();
    }
}