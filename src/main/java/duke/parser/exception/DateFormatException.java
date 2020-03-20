package duke.parser.exception;

/**
 * Represents an error when parsing a date with an invalid format.
 */
public class DateFormatException extends ParseException {
    /**
     * Constructs a new {@code DateFormatException} when parsing a date with an invalid format.
     */
    public DateFormatException() {
        super("I need the date in this format:\n yyyy-MM-dd.");
    }
}
