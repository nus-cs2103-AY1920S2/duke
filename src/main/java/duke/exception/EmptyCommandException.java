package duke.exception;

/**
 * Thrown when user input is empty.
 */
public class EmptyCommandException extends DukeException {
    public EmptyCommandException() {
        super("\n" + "Please do not leave your commands empty!");
    }
}
