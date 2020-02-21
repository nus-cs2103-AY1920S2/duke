package duke.exception;

/**
 * The duke.exception.DukeException class represents errors that may occur while running the Duke program
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
