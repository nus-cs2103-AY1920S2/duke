package duke.commons.exceptions;

/**
 * Represents an exception thrown when the input task do not have description.
 */

public class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException(String message) {
        super(message);
    }
}