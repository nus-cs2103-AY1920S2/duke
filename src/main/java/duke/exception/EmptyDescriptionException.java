package duke.exception;

/**
 * Represents the exception thrown when the user input contains an empty description for tasks.
 */
public class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException(String message) {
        super(message);
    }
}