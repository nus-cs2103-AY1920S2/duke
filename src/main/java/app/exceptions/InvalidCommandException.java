package app.exceptions;

/**
 * This class represents the exception that is thrown
 * when the user tries to use an unsupported command.
 */
public class InvalidCommandException extends BaseException {
    /**
     * Initialises a new InvalidCommandException object with a
     * custom error message.
     * @param message The error message
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}