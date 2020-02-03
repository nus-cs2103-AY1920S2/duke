package app.exceptions;

/**
 * This class represents the exception thrown when the user
 * fails to provide certain arguments required by the command
 */
public class WrongUsageException extends BaseException {
    /**
     * Initialises a new WrongUsageException object with a
     * custom error message
     * @param message The error message
     */
    public WrongUsageException(String message) {
        super(message);
    }
}