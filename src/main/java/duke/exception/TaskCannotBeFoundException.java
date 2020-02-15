package duke.exception;

/**
 * Indicates an exception when task cannot be found.
 */
public class TaskCannotBeFoundException extends DukeException {
    /**
     * Constructs a TaskCannotBeFoundException.
     */
    public TaskCannotBeFoundException() {
        super("Task cannot be found.");
    }
}
