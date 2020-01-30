/**
 * The TaskList class is a class that encapsulates a list containing various tasks, and handles methods relating
 * to creating and deleting tasks.
 */
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
     * Adds a new Task to this TaskList.
     * @param task the new Task to be added into this list.
     * @return a String message indicating if the adding is successful.
     */
    public String add(Task task) {
        this.list.add(task);
        return Ui.newTaskMessage + task + Ui.taskCountMessage(this.list.size());
    }

    /**
     * Adds a new Task of type Todo to this TaskList.
     * @param isDone if this Task is done.
     * @param taskName the name of this Task.
     * @return a function call to the add() function.
     */
    public String newTodo(boolean isDone, String taskName) {
        Task task = new Todo(isDone, taskName);
        return this.add(task);
    }

    /**
     * Adds a new Task of type Event to this TaskList.
     * @param isDone if this Task is done.
     * @param taskName the name of this Task.
     * @param taskTime the end time of this Task.
     * @return a function call to the add() function.
     */
    public String newEvent(boolean isDone, String taskName, String taskTime) {
        Task task = new Event(isDone, taskName, taskTime);
        return this.add(task);
    }

    /**
     * Adds a new Task of type Deadline to this TaskList.
     * @param isDone if this Task is done.
     * @param taskName the name of this Task.
     * @param taskTime the end time of this Task.
     * @return a function call to the add() function.
     */
    public String newDeadline(boolean isDone, String taskName, String taskTime) {
        Task task = new Deadline(isDone, taskName, taskTime);
        return this.add(task);
    }

    /**
     * Marks a Task in this list as done.
     * @param taskID the position (starting from 1) of the task in the list.
     * @return a function call to markDone() in the Task class.
     */
    public String markDone(int taskID) {
        return this.list.get(taskID - 1).markDone();
    }

    /**
     * Deletes a Task from this list.
     * @param taskID the position (starting from 1) of the task in the list.
     * @return a String message indicating if the deletion is successful.
     */
    public String deleteTask(int taskID) {
        String output = Ui.deleteTaskMessage + this.list.get(taskID - 1);
        this.list.remove(taskID - 1);
        output = output + Ui.taskCountMessage(this.list.size());
        return output;
    }

    /**
     * Returns the size of this list.
     * @return the size of the list.
     */
    public int getSize() {
        return this.list.size();
    }

    /**
     * Returns this list.
     * @return the list.
     */
    public ArrayList<Task> getList() {
        return this.list;
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