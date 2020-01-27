package dukebot.tasklist;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Generates a new TaskList.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns size of TaskList.
     *
     * @return Size of TaskList.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Gets task at specified index.
     *
     * @param taskInd  Index of task.
     */
    public Task getTask(int taskInd) {
        return this.taskList.get(taskInd);
    }

    /**
     * Gets TaskList.
     *
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Adds Task to end of TaskList.
     *
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Deletes task at specified index.
     *
     * @param taskInd  Index of task.
     */
    public Task deleteTask(int taskInd) {
        if (taskInd >= size() || taskInd < 0) {
            return null;
        } else {
            Task task = this.taskList.get(taskInd);
            this.taskList.remove(taskInd);
            return task;
        }
    }

}