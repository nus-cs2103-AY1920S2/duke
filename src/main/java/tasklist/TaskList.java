package tasklist;

import task.Task;

import java.util.ArrayList;

/**
 * This class contains the task list.
 */
public class TaskList {

    private ArrayList<Task> tasksList;

    /**
     * Constructor for creating new TaskList object and ArrayList<Task></Task> when there is no existing file.
     */
    public TaskList() {
        this.tasksList = new ArrayList<>();
    }

    /**
     * Constructor for creating new TaskList object taking in an existing tasksList.
     *
     * @param tasksList ArrayList containing Tasks.
     */
    public TaskList(ArrayList<Task> tasksList) {
        this.tasksList = tasksList;
    }

    /**
     * This method retrieves the Task in TaskList.
     *
     * @return required Tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasksList;
    }

    /**
     * This method retrieves the total number of Tasks in TaskList.
     *
     * @return total number of Tasks in TaskList.
     */
    public int getNumTasks() {
        return this.tasksList.size();
    }

    /**
     * This method adds a new Task into TaskList.
     *
     * @param t This is the Task to be added into TaskList.
     */
    public void addTask(Task t) {
        this.tasksList.add(t);
    }

    /**
     * This method removed Task from TaskList.
     *
     * @param index This is the index of the Task in TaskList to be removed.
     * @return Task to be removed from TaskList.
     */
    public Task removeTask(int index) {
        Task t = this.tasksList.get(index);
        this.tasksList.remove(index);
        return t;
    }

    /**
     * This method mark a Task in TaskList as done.
     *
     * @param index This is the index of the Task in TaskList to be marked as done.
     * @return Task marked as done in TaskList.
     */
    public Task markTaskAsDone(int index) {
        Task t = this.tasksList.get(index);
        t.markAsDone();
        return t;
    }

}
