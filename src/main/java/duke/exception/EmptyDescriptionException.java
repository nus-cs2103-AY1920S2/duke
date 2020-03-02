package duke.exception;

/**
 * Represents the exception thrown when the user input contains an empty description for tasks.
 */
public class EmptyDescriptionException extends DukeException {
    /**
     * Constructs a fresh instance of an empty description exception.
     * @param message The exception message.
     */
    public EmptyDescriptionException(String message) {
        super(message);
    }
}