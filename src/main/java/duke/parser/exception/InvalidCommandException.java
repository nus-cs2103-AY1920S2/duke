package duke.parser.exception;

/**
 * Represents an error encountered when parsing an unknown command.
 */
public class InvalidCommandException extends ParseException {
    /**
     * Constructs a new {@code InvalidCommandException} when a
     * parsing an unknown command.
     */
    public InvalidCommandException() {
        super("What on earth does that mean?");
    }
}
