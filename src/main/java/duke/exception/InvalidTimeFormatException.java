package duke.exception;

/**
 * Represents the exception thrown when the user provides an invalid time format.
 */
public class InvalidTimeFormatException extends DukeException {
    /**
     * Constructs a fresh instance of an invalid time format exception.
     * @param message The exception message.
     */
    public InvalidTimeFormatException(String message) {
        super(message);
    }
}