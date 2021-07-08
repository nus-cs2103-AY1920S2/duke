package dukebot.tasklist;

import java.util.ArrayList;

/**
 * Represents the task list.
 */
public class TaskList {
    private final ArrayList<Task> taskList;

    /**
     * Generates a new TaskList.
     *
     * @param taskList The task list to use.
     */
    public TaskList(ArrayList<Task> taskList) {
        assert taskList != null;
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
     * @param taskInd Index of task.
     */
    public Task getTask(int taskInd) {
        if (taskInd >= taskList.size() || taskInd < 0) {
            return null;
        }
        return this.taskList.get(taskInd);
    }

    /**
     * Gets TaskList.
     *
     * @return The stored task list.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Adds Task to end of TaskList.
     *
     * @param task Task to add.
     */
    public void addTask(Task task) {
        assert task != null;
        this.taskList.add(task);
    }

    /**
     * Deletes task at specified index.
     *
     * @param taskInd Index of task.
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

    /**
     * Finds all Tasks whose names contain toFind.
     *
     * @param toFind Task to find.
     * @return ArrayList of tasks found.
     */
    public ArrayList<Task> findAll(String toFind) {
        ArrayList<Task> tasksFound = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getDescription().contains(toFind)) {
                tasksFound.add(task);
            }
        }
        return tasksFound;
    }

}