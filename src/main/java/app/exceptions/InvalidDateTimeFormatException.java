package app.exceptions;

/**
 * This class represents the exception thrown when the user
 * enters a date that does not conform to the supported formats.
 */
public class InvalidDateTimeFormatException extends BaseException {
    /**
     * Initialises a new InvalidDateTimeFormatException object with a
     * custom error message.
     * @param message The error message
     */
    public InvalidDateTimeFormatException(String message) {
        super(message);
    }
}