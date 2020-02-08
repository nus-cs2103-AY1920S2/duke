package duke.task;

import java.util.ArrayList;

/**
 * Represents a Collection of all Tasks created by User.
 */
public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Gets the list of tasks.
     * @return A list of tasks.
     */
    public ArrayList<Task> getList() {
        return this.taskList;
    }

    /**
     * Gets a specific task based on inputted task number.
     * @param taskNumber The index of a task in the TaskList.
     * @return Returns the corresponding task object.
     */
    public Task getTask(int taskNumber) {
        return taskList.get(taskNumber - 1);
    }

    /**
     * Adds a new task to TaskList.
     * @param task The task to be added to the list.
     * @return A TaskList object.
     */
    public TaskList addTask(Task task) {
        this.taskList.add(task);
        return this;
    }

    /**
     * Removes an existing task from TaskList.
     * @param taskNumber The index of the task to be removed in the TaskList.
     * @return Returns the deleted task.
     */
    public Task deleteTask(int taskNumber) {
        return this.taskList.remove(taskNumber - 1);
    }

    @Override
    public String toString() {
        String result = "";
        for (int currNum = 1; currNum <= taskList.size(); currNum++) {
            result = result + "\t" + currNum + ". " + taskList.get(currNum - 1) + "\n";
        }
        return result;
    }
}
