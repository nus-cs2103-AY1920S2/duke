package duke.exception;

/**
 * Exception for invalid commands.
 */
public class InvalidCommandException extends DukeException {
    /**
     * Constructs a new InvalidCommandException.
     *
     * @param errorMessage exception message.
     */
    public InvalidCommandException(String errorMessage) {
        super(errorMessage);
    }
}