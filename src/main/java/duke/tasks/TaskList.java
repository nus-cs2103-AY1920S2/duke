package duke.tasks;

import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the List of Task objects of this TaskList class.
     * @return List of Task objects of this class.
     */
    public List<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Sets isDone boolean to specified Task index in the Task List to be true.
     * @param i Index of Task.
     * @param taskList TaskList class to be operated on.
     * @return TaskList class that was operated on.
     */
    public static TaskList doneTask(int i, TaskList taskList) {
        taskList.getTaskList().get(i - 1).setDone();
        return taskList;
    }

    /**
     * Removes Task from List of Task of specified TaskList class.
     * @param i The index of Task in the TaskList to be removed.
     * @param taskList The TaskList class to be operated on.
     * @return The TaskList object operated on.
     */
    public static TaskList removeTask(int i, TaskList taskList) {
        taskList.getTaskList().remove(i - 1);
        return taskList;
    }

    /**
     * Adds a Task to the TaskList.
     * @param t The Task to be added to the TaskList.
     * @param taskList The TaskList to be operated on.
     * @return The TaskList object operated on.
     */
    public static TaskList addTask(Task t, TaskList taskList) {
        taskList.getTaskList().add(t);
        return taskList;
    }
}
