package bot.store;

import bot.task.Task;
import bot.task.Todo;

import bot.loadsave.LoadAndSave;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Class to handle stored items within the bot,
 * and mark them as done
 */
public class TaskStorage implements Storage<Task> {
    private final ArrayList<Task> storedTasks = new ArrayList<Task>();

    /**
     * Adds tasks from a Collection into this TaskStorage
     *
     * @param tasks The Collection to add from
     */
    public void importFromCollection(Collection<Task> tasks) {
        storedTasks.addAll(tasks);
    }

    /**
     * Adds a String to the stored items
     *
     * @param toStore The Task to be stored
     */
    @Override
    public void store(Task toStore) {
        this.storedTasks.add(toStore);
    }

    /**
     * Gets the Task that appears at index
     * in the stored Tasks list
     *
     * @param index Index of the Task as it appears
     *          in the storage
     */
    @Override
    public Task get(int index) {
        if (index <= 0 || index > this.getSize()) {
            // a placeholder task
            return new Todo("index out of bounds");
        } else {
            return this.storedTasks.get(index - 1);
        }
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
    @Override
    public String retrieve(int i) {
        if (i <= 0 || i > this.getSize()) {
            // index out of bounds
            return "";
        } else {
            return i + ". " + this.get(i);
        }
    }

    /**
     * Gets the number of Tasks currently logged.
     *
     * @return int representing number of Tasks.
     */
    @Override
    public int getSize() {
        return this.storedTasks.size();
    }

    /**
     * Removes an entry from the stored
     * items (at index i)
     *
     * @param i Index of the item as it appears
     *          in the list
     */
    @Override
    public void delete(int i) {
        if (i > 0 && i <= this.getSize()) {
            // index not out of bounds
            this.storedTasks.remove(i - 1);
        }
    }

    /**
     * Attempts to save the stored items
     * to a file on the local system
     *
     * @param ttd The LoadAndSave containing the
     *            correct file directory and name
     *            to save Tasks to
     */
    @Override
    public void saveToDisk(LoadAndSave<Task> ttd) {
        ttd.saveToDisk(this.storedTasks);
    }
}
