package duke.exception;

/**
 * Signals that an error has occurred in the application.
 */
public abstract class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
