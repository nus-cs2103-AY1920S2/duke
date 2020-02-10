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

    /**
     * Add a new task into the current list.
     *
     * @param mytask Task to be added.
     */
    public void add(Task mytask) {
        tasks.add(mytask);
        ui.addMessage((this.tasks).size(), mytask);
        storage.store(this, ui);
    }

    /**
     * Deletes the task at the particular index of the list.
     *
     * @param index index of the object in the list to be deleted.
     * @param storage Storage object to be used to store a task.
     */
    public void delete(int index, Storage storage) {
        // Split the string to get the
        // index of the task to be deleted
        int sizeBeforeDeletion = getsize();
        ui.deletedTaskMessage(getsize() - 1, tasks.get(index));
        tasks.remove(index); // Deletes from task list

        assert sizeBeforeDeletion - 1 == getsize() : "Task not deleted!";
        storage.store(this, ui);
    }

    /**
     * Finds the task that has description which is similar to the word
     * that is passed in as argument.
     *
     * @param word keyword to find in the list.
     */
    public void find(String word, Storage storage) {
        TaskList filteredlist= new TaskList(new ArrayList<Task>(), ui, storage);
        for (Task task : this.tasks) {
            String description = ((task.getDesc()).toLowerCase());

            if (description.matches("(?i)" + "(" + word.toLowerCase() + ")" + ".*"))
                filteredlist.add(task);
        }
        ui.foundMessage();
        ui.listAllTasks(filteredlist);
    }

    /**
     * This method returns the list to the caller.
     *
     * @return List<Task> A list containing the Tasks.
     */
    public List<Task> getListOfTask() {
        return tasks;
    }

    /**
     * Returns the number of items in the list.
     *
     * @return int number of items in the list.
     */
    public int getsize() {
        return tasks.size();
    }
}
