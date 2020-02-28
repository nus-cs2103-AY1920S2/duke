/**
 * The TaskList class is a class that encapsulates a list containing various tasks, and handles methods relating
 * to creating and deleting tasks.
 */

package duke;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

public class TaskList {
    private ArrayList<Task> list;

    private Comparator<Task> compareName = (a,b) -> a.getTaskName().compareTo(b.getTaskName());
    private Comparator<Task> compareDate = (a,b) -> a.getTaskDate().compareTo(b.getTaskDate());
    private Comparator<Task> compareTime = (a,b) -> a.getTaskTime().compareTo(b.getTaskTime());

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
    public String add(Task task) {
        this.list.add(task);
        return Ui.ADDED_NEW_TASK + task + Ui.taskCountMessage(this.list.size());
    }

    /**
     * Appends otherList into this TaskList.
     * @param otherList the taskList to append to this list.
     */
    public void addAll(TaskList otherList) {
        this.list.addAll(otherList.getList());
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
    public String markDone(int taskID) {
        String output = this.list.get(taskID - 1).markDone();
        return output;
    }

    /**
     * Deletes a Task from this list and saves the changes to the data file.
     * @param taskID the position (starting from 1) of the task in the list.
     * @return a String message indicating if the deletion is successful.
     * @throws IndexOutOfBoundsException if the specified taskID is not within the current range of the TaskList.
     * @throws IOException if the changes are unable to be saved into the data file.
     */
    public String deleteTask(int taskID) throws IndexOutOfBoundsException {
        String output = Ui.DELETED_TASK + this.list.get(taskID - 1);
        this.list.remove(taskID - 1);
        output = output + Ui.taskCountMessage(this.list.size());
        return output;
    }

    /**
     * Updates a Task in this list and saves the changes to the data file
     * @param taskID The position (starting from 1) of the task in the list.
     * @param fieldType The field to update.
     * @param fieldDescription The description within the field to update.
     * @return a String message indicating if the update is successful.
     * @throws IndexOutOfBoundsException if the specified taskID is not within the current range of the TaskList.
     * @throws IOException if the changes are unable to be saved into the data file.
     */
    public String updateTask(int taskID, String fieldType, String fieldDescription)
            throws IndexOutOfBoundsException {

        Task targetTask = this.list.get(taskID - 1);

        boolean assigningDateTimeForTodo = targetTask instanceof Todo &&
                (fieldType.equals("date") || fieldType.equals("time"));
        if (assigningDateTimeForTodo) {
            return Ui.CANNOT_SET_DATE_TIME_TO_TODO;
        }

        switch(fieldType) {
            case "name":
                targetTask.setTaskName(fieldDescription);
                break;
            case "date":
                targetTask.setTaskDate(fieldDescription);
                break;
            case "time":
                targetTask.setTaskTime(fieldDescription);
                break;
            default:
                return Ui.INVALID_FIELD;
        }

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
    public void sortName() {
        this.list.sort(compareName);
    }

    /**
     * Sorts this list by task date and time in natural order.
     */
    public void sortDateTime() {
        this.list.sort(compareDate.thenComparing(compareTime));
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