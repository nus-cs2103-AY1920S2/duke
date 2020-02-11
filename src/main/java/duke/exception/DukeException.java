package duke.exception;

/**
 * Parent exception to group all self declared exceptions.
 */
public class DukeException extends RuntimeException {
    public DukeException(String message) {
        super(message);
    }
}
