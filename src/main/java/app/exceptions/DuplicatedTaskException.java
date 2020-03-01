package app.exceptions;

/**
 * This class represents the exception that is thrown
 * when the user a duplicated task is added.
 */
public class DuplicatedTaskException extends BaseException {
    /**
     * Initialises a new DuplicatedTaskException object with a
     * custom error message.
     * @param message The error message
     */
    public DuplicatedTaskException(String message) {
        super(message);
    }
}