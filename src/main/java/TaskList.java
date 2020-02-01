import java.util.ArrayList;

/**
 * TaskList class that contains the ArrayList of Task and has methods to modify it.
 */
public class TaskList {

    private static ArrayList<Task> tasks;

    /**
     * Creates TaskList object.
     *
      * @param tasks An ArrayList of Task.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the size of the ArrayList.
     *
     * @return An Integer representing how many tasks the ArrayList holds.
     */
    public static int size() {
        return tasks.size();
    }

    /**
     * Returns the ArrayList containing the Task.
     *
     * @return An ArrayList of Task.
     */
    public static ArrayList<Task> getList() {
        return tasks;
    }

    /**
     * Adds task to the ArrayList of Task.
     * @param t A task that will be added.
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Deletes the task from the ArrayList of Task.
     *
      * @param deletionNumber An Integer representing the task index to be deleted.
     */
    public void delete(int deletionNumber) {
        tasks.remove(deletionNumber - 1);
    }

    /**
     * Marks the Task in the ArrayList as done.
     *
     * @param number An Integer representing the task index to be marked as done.
     */
    public void markDone(int number) {
        tasks.get(number - 1).markAsDone();
    }
}