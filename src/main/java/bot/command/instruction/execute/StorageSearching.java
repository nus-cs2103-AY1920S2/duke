package bot.command.instruction.execute;

import bot.store.Storage;
import bot.Ui;

/**
 * An interface that represents Instructions that
 * need to read from the Storage
 */
public interface StorageSearching<T> {
    /**
     * Searches the store and sends a message to the UI,
     * given the application's storage and UI objects,
     * and the search term
     *
     * @param store The Storage to read
     * @param ui The Ui to display messages to
     */
    void searchStore(Storage<T> store, Ui ui, String searchTerm);
}
