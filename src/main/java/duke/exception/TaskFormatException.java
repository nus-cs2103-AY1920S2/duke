package duke.exception;

/**
 * Represents the exception thrown when the user inputs an invalid command not recognised by Duke.
 */
public class TaskFormatException extends DukeException {
    public TaskFormatException(String message) {
        super(message);
    }
}