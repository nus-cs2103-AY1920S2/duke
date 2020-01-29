import java.util.ArrayList;
import java.util.Optional;

/**
 * Class to handle stored items
 * within the bot, and mark them
 * as done
 */
public class Storage {
    private final ArrayList<Task> storage = new ArrayList<Task>();

    /**
     * Adds a String to the stored items
     *
     * @param toStore The Task to be stored
     */
    public void store(Task toStore) {
        this.storage.add(toStore);
    }

    /**
     * Prints out all the stored items,
     * in order which they were stored
     */
    public void readStorage() {
        int length = this.storage.size();
        for (int i = 0; i < length; i++) {
            System.out.println(retrieve(i + 1));
        }
    }

    /**
     * Searches the Storage for a Task with a
     * particular date
     *
     * @param date String representing date
     *
     * @return An Optional containing the Task, if found
     */
    public Optional<ArrayList<Integer>> searchStorage(String date) {
        // parse the String
        PrettyTime pt = new PrettyTime(date);
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        int size = this.storage.size();

        for (int i = 0; i < size; i++) {
            if (this.storage.get(i).getPrettyTime().equals(pt)) {
                indexes.add(i + 1);
            }
        }

        if (indexes.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(indexes);
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
        this.storage.get(index - 1).markAsDone();
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
        return i + ". " + this.storage.get(i - 1);
    }

    /**
     * Gets the number of Tasks currently logged.
     *
     * @return int representing number of Tasks.
     */
    public int getNumTasks() {
        return this.storage.size();
    }

    /**
     * Removes an entry from the stored
     * items (at index i)
     *
     * @param i Index of the item as it appears
     *          in the list
     */
    public void delete(int i) {
        this.storage.remove(i - 1);
    }
}
