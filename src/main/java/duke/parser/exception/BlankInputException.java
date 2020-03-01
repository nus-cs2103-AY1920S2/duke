package duke.parser.exception;

/**
 * Represents an error encountered when a user input is blank.
 */
public class BlankInputException extends ParseException {
    /**
     * Constructs a new {@code BlankInputException} when a user input is blank.
     */
    public BlankInputException() {
        super("Speak up! I can't hear you.");
    }
}
