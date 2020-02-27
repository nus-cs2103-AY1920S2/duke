package duke;

/**
 * Thrown when data cannot be read from or written to storage.
 */
class StorageException extends DukeException {
    /**
     * Constructs a {@code StorageException} with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause
     */
    StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
