package duke.exception;

/**
 * Thrown when an error occurs with IO.
 */
public class DukeIoException extends DukeException {

    /**
     * Constructs a DukeIoException with specified message.
     *
     * @param message The specified message.
     */
    public DukeIoException(String message) {
        super(message);
    }
}
