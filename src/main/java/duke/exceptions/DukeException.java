package duke.exceptions;

/**
 * Exception thrown when Duke encounters an unknown user input.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

}
