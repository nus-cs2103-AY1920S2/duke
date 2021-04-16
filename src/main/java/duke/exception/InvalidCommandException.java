package duke.exception;

/**
 * Represents the exception thrown when the user inputs an invalid command not recognised by Duke.
 */
public class InvalidCommandException extends DukeException {
    /**
     * Constructs a fresh instance of an invalid command exception.
     * @param message The exception message.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}