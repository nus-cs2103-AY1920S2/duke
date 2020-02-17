package duke.exception;

/**
 * Signals that there is an error in the user's input command.
 */
public class IncorrectCommandException extends DukeException {
    /**
     * Constructs a new IncorrectCommandException with the specified error message.
     *
     * @param message The detail error message.
     */
    public IncorrectCommandException(String message) {
        super(message);
    }
}
