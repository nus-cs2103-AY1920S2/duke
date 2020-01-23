import java.util.ArrayList;

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
     * @param toStore The String to be stored
     */
    public void store(String toStore) {
        this.storage.add(new Task(toStore));
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
        return i + ". " + doneGet(i - 1)
                + " " + this.storage.get(i - 1);
    }
}
