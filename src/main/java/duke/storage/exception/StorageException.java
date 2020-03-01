package duke.storage.exception;

import duke.DukeException;

/**
 * Represents a storage error encountered when performing read/write operations in Duke.
 */
public class StorageException extends DukeException {
    /** The name of the file that caused the error. */
    private String filePath;
    /**
     * Constructs a new {@code StorageException} when performing read/write operations in Duke.
     *
     * @param message the message of the exception.
     * @param filePath the name of the file that caused the error.
     */
    public StorageException(String message, String filePath) {
        super(String.format("%s: %s", message, filePath));
        this.filePath = filePath;
    }

    /**
     * Returns the path to the file that caused the exception.
     *
     * @return the path to the file that caused the exception.
     */
    public String getFilePath() {
        return filePath;
    }
}
