import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileOutputStream;

/**
 * Class to handle stored items within the bot,
 * and mark them as done. Class Storage also
 * handles file writing and reading
 */
public class Storage {
    private final ArrayList<Task> storedTasks;

    /**
     * Constructs a new Storage, loading saved items
     * from the local file system if possible. If not,
     * an empty Storage is created.
     */
    public Storage() {
        this.storedTasks = new ArrayList<Task>();
    }

    /**
     * Adds a String to the stored items
     *
     * @param toStore The Task to be stored
     */
    public void store(Task toStore) {
        this.storedTasks.add(toStore);
    }

    /**
     * Prints out all the stored items,
     * in order which they were stored
     */
    public void readStorage() {
        int length = this.storedTasks.size();
        for (int i = 0; i < length; i++) {
            System.out.println(retrieve(i + 1));
        }
    }

    /**
     * Marks a specific item on the list
     * as "done"
     *
     * @param index The index of the item,
     *              as it appears in the list
     */
    public void markAsDone(int index) {
        this.storedTasks.get(index - 1).markAsDone();
    }

    /**
     * Retrieves an entry from the stored
     * items (at index i)
     *
     * @param i Index of the item as it
     *          appears in the list
     * @return A String representing the
     *         item, its position on the list,
     *         and its "done" status
     */
    public String retrieve(int i) {
        return i + ". " + this.storedTasks.get(i - 1);
    }

    /**
     * Gets the number of Tasks currently logged.
     *
     * @return int representing number of Tasks.
     */
    public int getNumTasks() {
        return this.storedTasks.size();
    }

    /**
     * Removes an entry from the stored
     * items (at index i)
     *
     * @param i Index of the item as it appears
     *          in the list
     */
    public void delete(int i) {
        this.storedTasks.remove(i - 1);
    }

    /**
     * Attempts to save the stored items
     * to a file on the local system
     */
    public void saveToDisk() {
        String toBeSaved = "";
        for (Task task : storedTasks) {
            // use line breaks to separate the tasks
        }
    }
}
