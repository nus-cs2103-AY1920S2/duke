package duke.exception;

/**
 * DukeException class handles errors in user input.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}