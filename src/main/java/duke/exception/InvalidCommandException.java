package duke.exception;

public class InvalidCommandException extends DukeException {

    /**
     * Create instance of InvalidCommandException.
     *
     * @param message Error message of exception
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
