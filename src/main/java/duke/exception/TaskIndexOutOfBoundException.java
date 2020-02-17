package duke.exception;

/**
 * Signals that the task index specified is out of bound for that TaskList.
 */
public class TaskIndexOutOfBoundException extends DukeException {
    /**
     * Constructs a new TaskIndexOutOfBoundException with the specified error message.
     *
     * @param message The detail error message.
     */
    public TaskIndexOutOfBoundException(String message) {
        super(message);
    }
}
