package duke.parser.exception;

/**
 * Represents an error when parsing a time with an invalid format.
 */
public class TimeFormatException extends ParseException {
    /**
     * Constructs a new {@code TimeFormatException} when parsing a time with an invalid format.
     */
    public TimeFormatException() {
        super("I need the time in 24-hour format:\n HH:mm.");
    }
}
