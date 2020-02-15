package duke.exception;

/**
 * Exception for invalid date format.
 */
public class InvalidDateFormatException extends DukeException {
    /**
     * Constructs a new InvalidDateFormatException.
     *
     * @param errorMessage exception message.
     */
    public InvalidDateFormatException(String errorMessage) {
        super(errorMessage);
    }
}
