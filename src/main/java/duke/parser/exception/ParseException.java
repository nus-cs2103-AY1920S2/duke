package duke.parser.exception;

import duke.DukeException;

/**
 * Represents an error encountered when parsing user input.
 */
public class ParseException extends DukeException {
    /**
     * Constructs a new {@code ParseException} when parsing user input.
     *
     * @param message the message the message of the exception.
     */
    public ParseException(String message) {
        super(message);
    }
}
