package duke.exception;

/**
 * Thrown when user inputs an empty command.
 */
public class EmptyCommandException extends DukeException {
    public EmptyCommandException() {
        super("\n" + "Please use me properly dude! Do not leave your commands empty.");
    }
}

