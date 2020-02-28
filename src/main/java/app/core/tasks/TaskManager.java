package app.core.tasks;

import java.util.List;
import java.util.ArrayList;

import app.util.Date;
import app.core.Messages;
import app.core.StorageManager;
import app.exceptions.StorageFileException;
import app.exceptions.DuplicatedTaskException;
import app.exceptions.EmptyTaskListException;

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
     * @return The Task object that was created
     * @throws StorageFileException If an error happens while saving the task list
     * @throws DuplicatedTaskException If the task is duplicated
     */
    public Task addTodoTask(String description) throws StorageFileException, DuplicatedTaskException {
        return this.add(new Task(description));
    }

    /**
     * Adds a deadline task in the manager.
     * @param description The description of the task
     * @param deadline A Date object representing the deadline 
     *     of the task
     * @return The Task object that was created
     * @throws StorageFileException If an error happens while saving the task list
     * @throws DuplicatedTaskException If the task is duplicated
     */
    public Task addDeadlineTask(String description, Date deadline)
            throws StorageFileException, DuplicatedTaskException {
        return this.add(new DeadlineTask(description, deadline));
    }

    /**
     * Adds an event task in the manager.
     * @param description The description of the task
     * @param when A Date object representing the date of 
     *     the event
     * @return The Task object that was created
     * @throws StorageFileException If an error happens while saving the task list
     * @throws DuplicatedTaskException If the task is duplicated
     */
    public Task addEventTask(String description, Date when) throws StorageFileException, DuplicatedTaskException {
        return this.add(new EventTask(description, when));
    }

    private Task add(Task task) throws StorageFileException, DuplicatedTaskException {
        if (this.taskList.contains(task)) {
            throw new DuplicatedTaskException(Messages.ADD_DUPLICATED_TASK_MESSAGE);
        }

        this.taskList.add(task);
        this.storageManager.save(this.taskList);
        return task;
    }

    /**
     * Sets a task as done.
     * @param index The index of the task in the task manager
     * @return The Task object that was updated
     * @throws IndexOutOfBoundsException If the index is out of the bounds of the task list
     * @throws StorageFileException If an error happens while saving the task list
     */
    public Task setTaskDone(int index) throws IndexOutOfBoundsException, StorageFileException {
        Task task = this.taskList.get(index - 1);
        task.setDone();
        this.storageManager.save(this.taskList);
        return task;
    }

    /**
     * Deletes a task from the task manager.
     * @param index The index of the task in the task manager
     * @return The Task object that was deleted
     * @throws IndexOutOfBoundsException If the index is out of the bounds of the task list
     * @throws StorageFileException If an error happens while saving the task list
     */
    public Task deleteTask(int index) throws IndexOutOfBoundsException, StorageFileException {
        Task task = this.taskList.remove(index - 1);
        this.storageManager.save(this.taskList);
        return task;
    }

    /**
     * Finds a list of tasks that matches with an input string.
     * @param toMatch The string to match in task descriptions
     * @return The string representation of the list of filtered tasks that matches the input string
     * @throws EmptyTaskListException If the task list is empty
     */
    public String findMatchingTasks(String toMatch) throws EmptyTaskListException {
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : this.taskList) {
            if (task.getDescription().contains(toMatch)) {
                filteredTasks.add(task);
            }
        }

        if (filteredTasks.size() == 0) {
            throw new EmptyTaskListException(Messages.FIND_NO_TASKS_MESSAGE);
        }
        return convertListToString(filteredTasks);
    }

    /**
     * Returns a String representation of the list of tasks.
     * @return The string representation of the list of tasks
     * @throws EmptyTaskListException If the task list is empty
     */
    public String getTasks() throws EmptyTaskListException {
        if (this.taskList.size() == 0) {
            throw new EmptyTaskListException(Messages.LIST_NO_TASK_MESSAGE);
        }
        return convertListToString(this.taskList);
    }

    /**
     * Returns the size of the task list.
     * @return An integer representing the size of the task list
     */
    public int getSize() {
        return this.taskList.size();
    }

    private static String convertListToString(List<Task> taskList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, taskList.get(i)));
        }
        return sb.toString();
    }
}