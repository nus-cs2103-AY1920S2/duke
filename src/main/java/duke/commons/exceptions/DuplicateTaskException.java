package duke.commons.exceptions;

/**
 * Represents an exception thrown when the item to be added has already existed.
 */

public class DuplicateTaskException extends DukeException {
    public DuplicateTaskException(String message) {
        super(message);
    }
}
