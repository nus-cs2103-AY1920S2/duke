package bot;

import bot.task.Task;
import bot.util.PrettyTime;
import bot.loadsave.LoadAndSave;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

/**
 * Class to handle stored items within the bot,
 * and mark them as done
 */
public class Storage {
    private final ArrayList<Task> storedTasks = new ArrayList<Task>();

    /**
     * Adds tasks from a Collection into this Storage
     *
     * @param tasks The Collection to add from
     */
    public void importTasks(Collection<Task> tasks) {
        storedTasks.addAll(tasks);
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
    public void printStorage() {
        int length = this.storedTasks.size();
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
        int size = this.storedTasks.size();

        if (pt.hasTime()) {
            for (int i = 0; i < size; i++) {
                if (this.storedTasks.get(i).getPrettyTime().equals(pt)) {
                    indexes.add(i + 1);
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (this.storedTasks.get(i).getPrettyTime().matchDate(pt)) {
                    indexes.add(i + 1);
                }
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
     *
     * @param ttd The LoadAndSave containing the
     *            correct file directory and name
     *            to save Tasks to
     */
    public void saveToDisk(LoadAndSave<Task> ttd) {
        ttd.saveToDisk(this.storedTasks);
    }

    /**
     * Searches the Storage for a Task with a
     * particular word in its description
     *
     * @param searchTerm String representing keyword
     *                   to find
     *
     * @return An Optional containing the Task(s), if found
     */
    public Optional<ArrayList<Integer>> findInDesc(String searchTerm) {
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        int size = this.storedTasks.size();
        for (int i = 0; i < size; i++) {
            if (this.storedTasks.get(i).getTaskDetails().contains(searchTerm)) {
                indexes.add(i + 1);
            }
        }

        if (indexes.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(indexes);
        }
    }
}
