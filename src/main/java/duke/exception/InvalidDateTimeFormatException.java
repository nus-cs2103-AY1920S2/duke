package duke.exception;

/**
 * Indicates an exception when the format of the date or time is incorrect.
 */
public class InvalidDateTimeFormatException extends DukeException {
    /**
     * Constructs an InvalidDateTimeFormatException.
     * @param format The correct format required.
     */
    public InvalidDateTimeFormatException(String format) {
        super("Incorrect date or time format. Format required: " + format);
    }
}
