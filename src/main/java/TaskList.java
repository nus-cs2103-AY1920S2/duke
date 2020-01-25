import java.util.ArrayList;

/**
 * Represents a list of tasks. It has methods for handling the tasks,
 * such as adding a task, removing a task, getting a task,
 * returning the list of tasks, and setting a task to be done.
 */

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task t) {
        this.tasks.add(t);
    }

    public Task remove(int t) {
        Task task = this.tasks.get(t-1);
        this.tasks.remove(task);
        return task;
    }

    public void completedTask(Task task) {
        task.markDone();
    }

    public Task get(int num) {
        return this.tasks.get(num);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int numOfTasks() {
        return this.tasks.size();
    }
}
