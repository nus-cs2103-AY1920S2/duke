package bot.command.instruction.execute;

import bot.store.Storage;
import bot.Ui;

/**
 * An interface that represents Instructions that
 * need to write to the Storage
 */
public interface StorageWriting<T> {
    /**
     * Executes the desired change (writing to the
     * Storage) given the application's storage
     * and UI objects
     *
     * @param store The Storage to write to
     * @param ui The Ui to display messages to
     * @param item The item to store in Storage
     */
    void writeToStore(Storage<T> store, Ui ui, T item);
}
