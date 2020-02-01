package bot.command.instruction.execute;

import bot.Storage;
import bot.Ui;

/**
 * An interface that represents Instructions that
 * need to modify an item in the Storage
 */
public interface StorageModifying<T> {
    /**
     * Executes the desired change (writing to the
     * Storage) given the application's storage
     * and UI objects
     *
     * @param store The Storage to write to
     * @param ui The Ui to display messages to
     * @param index The index of the item to delete
     */
    void modifyStore(Storage<T> store, Ui ui, int index);
}
