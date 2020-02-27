package app.exceptions;

/**
 * This class represents the exception that is thrown
 * when the user tries to use an unsupported command.
 */
public class EmptyTaskListException extends BaseException {
    /**
     * Initialises a new EmptyTaskListException object with a
     * custom error message.
     * @param message The error message
     */
    public EmptyTaskListException(String message) {
        super(message);
    }
}