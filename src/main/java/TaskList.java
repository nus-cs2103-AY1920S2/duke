import java.util.ArrayList;
import java.util.List;

/**
 * TaskList class.
 * Keeps the tasks as a list of this instance.
 *
 * @author Amos Cheong
 */
public class TaskList {

    private List<Task> listOfTask;
    private Ui ui;
    private Storage storage;

    public TaskList() {};

    public TaskList(List<Task> listOfTask, Ui ui, Storage storage) {
        this.listOfTask = listOfTask;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Add a new task into the current list.
     * @param mytask Task to be added.
     */
    public void add(Task mytask) {
        listOfTask.add(mytask);
        ui.addMessage((this.listOfTask).size(), mytask);
        storage.store(this, ui);
    }

    /**
     * Deletes the task at the particular index of the list.
     * @param index index of the object in the list to be deleted.
     * @param storage Storage object to be used to store a task.
     */
    public void delete(int index, Storage storage) {
        // Split the string to get the
        // index of the task to be deleted
        ui.deletedTaskMessage(getsize() - 1, listOfTask.get(index));
        listOfTask.remove(index); // Deletes from task list

        storage.store(this, ui);
    }

    /**
     * Finds the task that has description which is similar to the word
     * that is passed in as argument.
     * @param word keyword to find in the list.
     */
    public void find(String word, Storage storage) {
        TaskList filteredlist= new TaskList(new ArrayList<Task>(), ui, storage);
        for (Task task : this.listOfTask) {
            String description = ((task.getDesc()).toLowerCase());

            if (description.matches("(?i)" + "(" + word.toLowerCase() + ")" + ".*"))
                filteredlist.add(task);
        }
        ui.foundMessage();
        ui.listAllTasks(filteredlist);
    }

    /**
     * This method returns the list to the caller.
     * @return List<Task> A list containing the Tasks.
     */
    public List<Task> getListOfTask() {
        return listOfTask;
    }

    /**
     * Returns the number of items in the list.
     * @return int number of items in the list.
     */
    public int getsize() {
        return listOfTask.size();
    }
}
