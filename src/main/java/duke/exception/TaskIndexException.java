package duke.exception;

/**
 * Represents the exception thrown when the user inputs an invalid task index.
 */
public class TaskIndexException extends DukeException {
    /**
     * Constructs a fresh instance of the task index exception.
     * @param message The exception message.
     */
    public TaskIndexException(String message) {
        super(message);
    }
}