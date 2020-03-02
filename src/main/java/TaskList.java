import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Returns the task list.
     *
     * @return task list.
     */
    public ArrayList<Task> getList() {
        return taskList;
    }

    /**
     * Adds the task to the task list.
     *
     * @param task task to be added to the task list.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes specified task from the task list.
     *
     * @param task task to be deleted from the task list.
     */
    public void deleteTask(Task task) {
        taskList.remove(task);
    }
}
