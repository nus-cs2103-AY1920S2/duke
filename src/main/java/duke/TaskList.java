/**
 * The TaskList class is a class that encapsulates a list containing various tasks, and handles methods relating
 * to creating and deleting tasks.
 */

package duke;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    /**
     * The constructor for a new TaskList object.
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Adds a new Task to this TaskList and saves the changes to the data file.
     * @param task the new Task to be added into this list.
     * @return a String message indicating if the adding is successful.
     * @throws IOException if the changes are unable to be saved into the data file.
     */
    public String add(Task task) throws IOException {
        this.list.add(task);
        Storage.save(this);
        return Ui.ADDED_NEW_TASK + task + Ui.taskCountMessage(this.list.size());
    }

    /**
     * Adds a new Task to this TaskList but does not write to the data file.
     * This method should only be called when the 'find' or 'list ...' command is executed.
     * @param task the new Task to be added into this list.
     */
    public void tempAdd(Task task) {
        this.list.add(task);
    }

    /**
     * Adds a new Task of type Todo to this TaskList and saves the changes to the data file.
     * @param isDone if this Task is done.
     * @param taskName the name of this Task.
     * @return a function call to the add() function.
     * @throws IOException if the changes are unable to be saved into the data file.
     */
    public String newTodo(boolean isDone, String taskName) throws IOException {
        Task newTask = new Todo(isDone, taskName);
        return this.add(newTask);
    }

    /**
     * Adds a new Task of type Event to this TaskList and saves the changes to the data file.
     * @param isDone if this Task is done.
     * @param taskName the name of this Task.
     * @param taskDateTime the end date-time of this Task.
     * @return a function call to the add() function.
     * @throws IOException if the changes are unable to be saved into the data file.
     */
    public String newEvent(boolean isDone, String taskName, LocalDateTime taskDateTime) throws IOException {
        Task newTask = new Event(isDone, taskName, taskDateTime);
        return this.add(newTask);
    }

    /**
     * Adds a new Task of type Deadline to this TaskList and saves the changes to the data file.
     * @param isDone if this Task is done.
     * @param taskName the name of this Task.
     * @param taskDateTime the end date-time of this Task.
     * @return a function call to the add() function.
     * @throws IOException if the changes are unable to be saved into the data file.
     */
    public String newDeadline(boolean isDone, String taskName, LocalDateTime taskDateTime) throws IOException {
        Task newTask = new Deadline(isDone, taskName, taskDateTime);
        return this.add(newTask);
    }

    /**
     * Marks a Task in this list as done and saves the changes to the data file.
     * @param taskID the position (starting from 1) of the task in the list.
     * @return a function call to markDone() in the Task class.
     * @throws IOException if the changes are unable to be saved into the data file.
     */
    public String markDone(int taskID) throws IOException {
        String output = this.list.get(taskID - 1).markDone();
        Storage.save(this);
        return output;
    }

    /**
     * Deletes a Task from this list and saves the changes to the data file.
     * @param taskID the position (starting from 1) of the task in the list.
     * @return a String message indicating if the deletion is successful.
     * @throws IndexOutOfBoundsException if the specified taskID is not within the current range of the TaskList.
     * @throws IOException if the changes are unable to be saved into the data file.
     */
    public String deleteTask(int taskID) throws IndexOutOfBoundsException, IOException {
        String output = Ui.DELETED_TASK + this.list.get(taskID - 1);
        this.list.remove(taskID - 1);
        output = output + Ui.taskCountMessage(this.list.size());
        Storage.save(this);
        return output;
    }

    /**
     * Updates a Task in this list and saves the changes to the data file
     * @param taskID the position (starting from 1) of the task in the list.
     * @param parameter the parameters of this command, separated with a regex ", ".
     * @return a String message indicating if the update is successful.
     * @throws IndexOutOfBoundsException if the specified taskID is not within the current range of the TaskList.
     * @throws IOException if the changes are unable to be saved into the data file.
     */
    public String updateTask(int taskID, String parameter) throws IndexOutOfBoundsException, IOException {
        Task targetTask = this.list.get(taskID - 1);
        String[] fields = parameter.split(", ");
        if (fields.length == 1) {
            return Ui.NO_FIELD_TO_UPDATE;
        }

        boolean assigningDateTimeForTodo = targetTask instanceof Todo &&
                (fields[0].equals("date") || fields[0].equals("time"));
        if (assigningDateTimeForTodo) {
            return Ui.CANNOT_SET_DATE_TIME_TO_TODO;
        }

        switch(fields[0]) {
        case "name":
            targetTask.setTaskName(fields[1]);
            break;
        case "date":
            targetTask.setTaskDate(fields[1]);
            break;
        case "time":
            targetTask.setTaskTime(fields[1]);
            break;
        default:
            return Ui.INVALID_FIELD;
        }

        Storage.save(this);
        return Ui.UPDATED_TASK + targetTask;
    }

    /**
     * Checks if this list is empty
     * @return a boolean if the list is empty.
     */
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    /**
     * Returns this list.
     * @return the list.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Sorts this list by task time in natural order.
     */
    public void sortTime() {
        this.list.sort((a,b) -> a.getTaskTime().compareTo(b.getTaskTime()));
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < this.list.size(); i++) {
            output = output + (i + 1) + ". " + this.list.get(i) + "\n";
        }
        return output;
    }
}