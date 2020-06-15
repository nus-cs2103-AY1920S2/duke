package app.exceptions;

/**
 * This class represents the exception that is thrown
 * when the user uses an invalid character in the input.
 */
public class InvalidCharacterException extends BaseException {
    /**
     * Initialises a new InvalidCharacterException object with a
     * custom error message.
     * @param message The error message
     */
    public InvalidCharacterException(String message) {
        super(message);
    }
}