package duke.exception;

/**
 * Thrown when an error occurs due to index out of bounds.
 * Index is the index of list of tasks in Duke.
 */
public class DukeInvalidIndexException extends DukeException {

    public DukeInvalidIndexException() {
        super(Messages.MESSAGE_INVALID_INDEX);
    }
}
