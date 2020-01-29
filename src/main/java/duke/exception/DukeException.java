package duke.exception;

/**
 * Signals an error caused by any Duke app related error.
 */
public class DukeException extends Exception {
    public DukeException() {}

    /**
     * @param message Error message of exception
     */
    public DukeException(String message) {
        super(message);
    }
}
