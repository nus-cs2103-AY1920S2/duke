import java.util.ArrayList;
import java.util.List;

/**
 * TaskList class.
 * Keeps the tasks as a list of this instance.
 *
 * @author Amos Cheong
 */
public class TaskList {

    private List<Task> tasks;
    private Ui ui;
    private Storage storage;

    public TaskList() {};

    public TaskList(List<Task> tasks, Ui ui, Storage storage) {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
    }

    public Storage getStorage() {
        return this.storage;
    }
    /**
     * Add a new task into the current list.
     *
     * @param mytask Task to be added.
     */
    public void add(Task mytask) {
        tasks.add(mytask);

        // Update .txt file
        storage.updateFile(this, ui);
    }

    /**
     * Deletes the task at the particular index of the list.
     *
     * @param index index of the object in the list to be deleted.
     */
    public void delete(int index) {
        // Split the string to get the
        // index of the task to be deleted
        int sizeBeforeDeletion = this.getSize();
        tasks.remove(index); // Deletes from task list

        assert sizeBeforeDeletion - 1 == this.getSize() : "Task not deleted!";

        // Update .txt file
        storage.updateFile(this, ui);

    }

    /**
     * Finds the task that has description which is similar to the word
     * that is passed in as argument.
     *
     * @param word keyword to find in the list.
     * @return TaskList return a TaskList object which is a filtered list
     * where it contains the tasks that is related to the keyword input by user
     */
    public TaskList find(String word) {
        TaskList filteredlist= new TaskList(new ArrayList<Task>(), ui, storage);
        for (Task task : tasks) {
            String description = ((task.getDesc()).toLowerCase());

            if (description.matches("(?i)" + "(" + word.toLowerCase() + ")" + ".*"))
                filteredlist.add(task);
        }
        return filteredlist;
    }

    /**
     * This method returns the list to the caller.
     *
     * @return List<Task> A list containing the Tasks.
     */
    public List<Task> getListOfTask() {
        return this.tasks;
    }

    /**
     * Returns the number of items in the list.
     *
     * @return int number of items in the list.
     */
    public int getSize() {
        return tasks.size();
    }

    public boolean hasDuplicates(Task taskCheck) {
        for (Task task : tasks) {
            if (task.equals(taskCheck)) {
                // Duplicate found
                return true;
            }
        }

        return false;
    }
}
