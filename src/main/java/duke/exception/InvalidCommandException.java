package duke.exception;

/**
 * Represents the exception thrown when the user inputs an invalid command not recognised by Duke.
 */
public class InvalidCommandException extends DukeException {
    public InvalidCommandException(String message) {
        super(message);
    }
}