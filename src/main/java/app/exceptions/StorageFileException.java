package app.exceptions;

/**
 * This class represents the exception that is thrown when
 * the program is unable to write to the storage file.
 */
public class StorageFileException extends BaseException {
    /**
     * Initialises a new StorageFileException object with a
     * custom error message.
     * @param message The error message
     */
    public StorageFileException(String message) {
        super(message);
    }
}