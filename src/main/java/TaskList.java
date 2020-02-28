import java.util.ArrayList;

/**
 * Represents a list of all tasks
 */

public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Creates a new task list with no task
     */

    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    /**
     * Creates a new task list with the input tasks
     * @param list This is the list of the tasks to be put inside the task list
     * @throws DukeException This is the DukeException
     */

    public TaskList(ArrayList<Task> list) throws DukeException {
        if (list.isEmpty()) throw new DukeException("Wrong input");
        else this.taskList = list;
    }

    /**
     * Adds a new task to the task list
     * @param task This is the task to be added to the list
     * @return The task that had been added to the list
     */

    public Task addTask(Task task) {
        this.taskList.add(task);
        return task;
    }

    /**
     * Returns the task list as an ArrayList of tasks
     * @return The task list as an ArrayList
     */

    public ArrayList<Task> getList() {
        return this.taskList;
    }

    /**
     * Returns the size of the current task list
     * @return the size of the current task list
     */

    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Retrieves a task from the task list
     * @param index This is the index of the task from the task list
     * @return The task at the index specified
     */

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    /**
     * Deletes a task from the task list
     * @param index This is the index of the task from the task list
     * @return The task deleted at the index specified
     */

    public Task removeTask(int index) {
        return this.taskList.remove(index);
    }
}
