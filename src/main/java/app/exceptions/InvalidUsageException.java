package app.exceptions;

/**
 * This class represents the exception thrown when the user
 * fails to provide certain arguments required by the command.
 */
public class InvalidUsageException extends BaseException {
    /**
     * Initialises a new InvalidUsageException object with a
     * custom error message.
     * @param message The error message
     */
    public InvalidUsageException(String message) {
        super(message);
    }
}