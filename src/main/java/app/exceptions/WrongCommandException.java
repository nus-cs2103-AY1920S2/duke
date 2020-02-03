package app.exceptions;

/**
 * This class represents the exception that is thrown
 * when the user tries to use an unsupported command
 */
public class WrongCommandException extends BaseException {
    /**
     * Initialises a new WrongCommandException object with a
     * custom error message
     * @param message The error message
     */
    public WrongCommandException(String message) {
        super(message);
    }
}