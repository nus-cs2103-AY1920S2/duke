package bot.store;

import bot.command.exception.InstructionAlreadyExistsException;
import bot.loadsave.LoadAndSave;

import java.util.Collection;

/**
 * Implementation of a generic item storage to be
 * used in 4LC3N-BOT
 *
 * @param <T> The type of items stored
 */
public interface Storage<T> {
    /**
     * Stores an item in Storage
     *
     * @param toStore The item to be stored
     */
    void store(T toStore) throws InstructionAlreadyExistsException;

    /**
     * Saves the content of this Storage to
     * the disk
     *
     * @param disk The LoadAndSave handling loading
     *             from and writing to disk
     */
    void saveToDisk(LoadAndSave<T> disk);

    /**
     * Gets an entry from the stored
     * items (at index i)
     *
     * @param i Index of the item as it appears
     *          in the storage
     */
    T get(int i);

    /**
     * Retrieves an entry from the stored
     * items (at index i)
     *
     * @param i Index of the item as it
     *          appears in the list
     * @return A String representing the item
     */
    String retrieve(int i);

    /**
     * Removes an entry from the stored
     * items (at index i)
     *
     * @param i Index of the item as it appears
     *          in the storage
     */
    void delete(int i);

    /**
     * Gets the number of items stored here
     *
     * @return Returns an Integer representing
     *     number of stored items
     */
    int getSize();

    /**
     * Adds items from a Collection into this Storage
     *
     * @param items The Collection to add from
     */
    void importFromCollection(Collection<T> items);
}
