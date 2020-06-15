package app.exceptions;

/**
 * This class represents the exception that is thrown when
 * the user tries to access an invalid index in the task list.
 */
public class InvalidTaskIndexException extends BaseException {
    /**
     * Initialises a new InvalidTaskIndexException object with a
     * custom error message.
     * @param message The error message
     */
    public InvalidTaskIndexException(String message) {
        super(message);
    }
}