package duke.exception;

/**
 * Indicates an exception when a task already done is to be marked as done.
 */
public class DoneException extends DukeException {
    /**
     * Constructs a DoneException.
     */
    public DoneException() {
        super("Task is already marked as done.");
    }
}
