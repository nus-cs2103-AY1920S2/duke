package duke.exception;

/**
 * Represents the exception thrown when the user inputs an invalid task format.
 */
public class TaskFormatException extends DukeException {
    /**
     * Constructs a fresh instance of a task format exception.
     * @param message The exception message.
     */
    public TaskFormatException(String message) {
        super(message);
    }
}