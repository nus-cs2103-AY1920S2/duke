package app.exceptions;

/**
 * This class represents the exception thrown when the user
 * enters a date that does not conform to the supported formats
 */
public class WrongDateTimeFormatException extends BaseException {
    /**
     * Initialises a new WrongDateTimeFormatException object with a
     * custom error message
     * @param message The error message
     */
    public WrongDateTimeFormatException(String message) {
        super(message);
    }
}