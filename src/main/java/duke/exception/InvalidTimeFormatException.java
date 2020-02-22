package duke.exception;

/**
 * Exception for invalid time format.
 */
public class InvalidTimeFormatException extends DukeException {
    /**
     * Constructs a new InvalidTimeFormatException.
     *
     * @param errorMessage exception message.
     */
    public InvalidTimeFormatException(String errorMessage) {
        super(errorMessage);
    }
}
