import java.util.ArrayList;

public class TaskList {


    private ArrayList<Task> tasks;

    /**
     * Constructor for the class that stores all the tasks
     * @param tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }



    /**
     * Check if there is any tasks
     * @return if there is any tasks
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Check the size of this instance of TaskList
     * @return the size of the TaskList
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Gets the indexed task from tasklist
     * @param i is the index that user want
     * @return the indexed task
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Adds the task to the current instance of TaskList
     * @param task is the task to be added
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes the task to the current instance of TaskList
     * @param toDelete is the index of task to be removed
     */
    public void remove(int toDelete) {
        tasks.remove(toDelete);
    }
}
