package duke.exception;

/**
 * Exception for the duke application.
 */
public class DukeException extends Exception {
    /**
     * Constructs a new DukeException.
     *
     * @param errorMessage exception message.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
