package duke.exception;

/**
 * Indicates an exception when the task cannot be updated.
 */
public class UpdateException extends DukeException {
    /**
     * Constructs an UpdateException.
     * @param message The message to be printed.
     */
    public UpdateException(String message) {
        super(message);
    }
}
