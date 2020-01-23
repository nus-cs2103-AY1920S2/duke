import java.util.ArrayList;

/**
 * Class to handle stored items
 * within the bot
 */
public class Storage {
    private final ArrayList<String> storage = new ArrayList<String>();

    /**
     * Adds a String to the stored items
     *
     * @param toStore The String to be stored
     */
    public void store(String toStore) {
        this.storage.add(toStore);
    }

    /**
     * Prints out all the stored items,
     * in order which they were stored
     */
    public void readStorage() {
        int length = this.storage.size();
        for (int i = 0; i < length; i++) {
            System.out.println((i + 1) + ". " + this.storage.get(i));
        }
    }
}
