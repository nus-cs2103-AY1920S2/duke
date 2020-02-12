package duke.exception;

/**
 * Indicates an exception in the storage.
 */
public class StorageException extends DukeException {
    /**
     * Constructs a StorageException.
     * @param message The detail message.
     */
    public StorageException(String message) {
        super(message);
    }
}
