package duke.main;

import duke.exceptions.InvalidArgumentException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskList class to handle all operations related to the lists of tasks
 */
public class TaskList {
    List<Task> taskList;
    Storage storage = null;

    /**
     * Default constructor for TaskList
     */
    public TaskList() {
        taskList = new ArrayList<>();
        storage = new Storage("data/duke.txt");
    }

    /**
     * Constructor for TaskList with Storage support
     * @param storage Storage object to handle load and save commands
     */
    public TaskList(Storage storage) {
        taskList = new ArrayList<>();
        taskList.addAll(storage.getTasks());
        this.storage = storage;
    }

    /**
     * Get a Task object based on an integer index
     * @param index Integer index to retrieve task Object from
     * @return Task object
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Adds a Task object to the list of tasks
     * @param task Task object to be added
     * @return Integer index of the Task added in list
     */
    public int addTask(Task task) {
        taskList.add(task);
        return taskList.size() - 1;
    }

    /**
     * Gets the number of tasks in the TaskList
     * @return Integer number of tasks
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Marks a given Task as done
     * @param indexString Index of the task as a String
     * @return Task object that was marked as done
     * @throws InvalidArgumentException when indexString is not a valid integer or points to an invalid item in the list
     */
    public Task markAsDone(String indexString) throws InvalidArgumentException {
        try {
            int index = Integer.parseInt(indexString);
            taskList.get(index - 1).markAsDone();
            return taskList.get(index - 1);
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            throw new InvalidArgumentException();
        }
    }

    /**
     * Deletes a given Task
     * @param indexString Index of the task as a String
     * @return The deleted Task
     * @throws InvalidArgumentException when indexString is not a valid integer or points to an invalid item in the list
     */
    public Task deleteTask(String indexString) throws InvalidArgumentException {
        try {
            int index = Integer.parseInt(indexString);
            Task deletedTask = taskList.get(index - 1);
            taskList.remove(index - 1);
            return deletedTask;
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            throw new InvalidArgumentException();
        }
    }

    /**
     * Prints out a numbered list of current Tasks in the TaskList
     * @return String output
     */
    public List<String> printTaskList() {
        List<String> output = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            output.add((i + 1) + ". " + taskList.get(i));
        }
        return output;
    }

    /**
     * Gets the list of Tasks in TaskList
     * @return List of Tasks in TaskList
     */
    public List<Task> getTasks() {
        return taskList;
    }

    /**
     * Clears all current tasks in TaskList
     * @return
     */
    public boolean clearTasks() {
        taskList.clear();
        return true;
    }

    public boolean saveTasks() {
        try {
            storage.save(taskList);
        } catch (Exception ex) {
            Ui.printException(ex);
        }
        return true;
    }
}
