package duke.commons.exceptions;

/**
 * Represents an exception thrown when the input command is invalid.
 */

public class InvalidCommandException extends DukeException {
    public InvalidCommandException(String message) {
        super(message);
    }
}