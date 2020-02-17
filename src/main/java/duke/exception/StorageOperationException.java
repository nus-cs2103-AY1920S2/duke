package duke.exception;

/**
 * Signals that an error has occurred while performing a storage operation.
 */
public class StorageOperationException extends DukeException {
    /**
     * Constructs a new StorageOperationException with the specified error message.
     *
     * @param message The detail error message.
     */
    public StorageOperationException(String message) {
        super(message);
    }
}
