package duke.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the existing list of tasks user have stored including
 * the functionality of adding new tasks, deleting existing tasks
 * and basic getters to allow retrieval of information on list
 * of users task such as size of list, the list itself and specific
 * task.
 *
 * @author Kenny Ho
 */
public class TaskList {

    protected List<Task> userTasksList;

    /**
     * A constructor used to wrap the ArrayList of tasks.
     *
     * @param listOfTask duke.command.List of user tasks.
     */
    public TaskList(List<Task> listOfTask) {
        this.userTasksList = listOfTask;
    }

    /**
     * A Overload empty constructor
     */
    public TaskList() {
        this.userTasksList = new ArrayList<>();
    }

    /**
     * Returns the exact amount of existing user tasks.
     *
     * @return int representing size of list of tasks.
     */
    public int getSize() {
        return userTasksList.size();
    }

    /**
     * Returns the duke.task.Task object which is located at the index given.
     *
     * @param taskNumber Index of which task is located in the duke.command.List.
     * @return duke.task.Task located at the list of the index given.
     */
    public Task getTask(int taskNumber) {
        return userTasksList.get(taskNumber);
    }

    /**
     * Returns the exact copy of existing user list of tasks.
     *
     * @return duke.command.List of duke.task.Task objects
     */
    public List<Task> getListOfTasks() {
        return userTasksList;
    }

    /**
     * Adds duke.task.Task object given in the existing a duke.task.Task list.
     *
     * @param task duke.task.Task object to be added into the user list of tasks.
     */
    public void addTask(Task task) {
        userTasksList.add(task);
    }

    /**
     * Deletes duke.task.Task object given in the existing duke.task.Task list.
     *
     * @param taskNumber Index of duke.task.Task object in the list of duke.task.Task object(s).
     * @return duke.task.Task object that have been removed.
     * @throws IndexOutOfBoundsException if given parameter taskNumber is out of bound.
     */
    public Task deleteTask(int taskNumber) throws IndexOutOfBoundsException {
        return userTasksList.remove(taskNumber);
    }

    /**
     * @param keyword
     * @return
     */
    public List<Task> findTask(String keyword) {
        ArrayList<Task> listOfTasksFound = new ArrayList<>();
        for (Task task : userTasksList) {
            if (task.getDescription().contains(keyword)) {
                listOfTasksFound.add(task);
            }
        }
        return listOfTasksFound;
    }

}
