package duke.exception;

/**
 * Represents the exception thrown when the user provides an invalid time format.
 */
public class InvalidTimeFormatException extends DukeException {
    public InvalidTimeFormatException(String message) {
        super(message);
    }
}