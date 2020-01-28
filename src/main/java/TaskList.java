import java.util.ArrayList;

/**
 * TaskList stores the Array List of tasks. It also handles methods specific to the array list.
 */
public class TaskList {
    private ArrayList<Task> dukeList;

    public TaskList(ArrayList<Task> dukeList) {
        this.dukeList = dukeList;
    }

    public TaskList() {
        dukeList = new ArrayList<>();
    }

    /**
     * Get the list of tasks.
     * @return list of tasks.
     */
    public ArrayList<Task> getDukeList() {
        return dukeList;
    }

    /**
     * Add a task into list of tasks.
     * @param task task to be added into the list.
     */
    public void addTask(Task task) {
        dukeList.add(task);
    }

    /**
     * Remove task from list of tasks.
     * @param num Specifies the index-1 to be removed.
     */
    public void removeTask(int num) {
        dukeList.remove(num-1);
    }

    /**
     * Mark a task as done.
     * @param num Specifies the index-1 to be marked as done.
     */
    public void markDone(int num) {
        dukeList.get(num - 1).markAsDone();
    }

    /**
     * Get the size of the tasks in the list.
     * @return size of the list.
     */
    public int getSize() {
        return dukeList.size();
    }
}
