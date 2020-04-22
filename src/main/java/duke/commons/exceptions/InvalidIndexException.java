package duke.commons.exceptions;

/**
 * Represents an exception thrown when the input index is out of bound.
 */

public class InvalidIndexException extends DukeException {
    public InvalidIndexException(String message) {
        super(message);
    }
}
