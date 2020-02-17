package duke.exception;

/**
 * Thrown when user inputs an unknown command.
 */
public class InvalidCommandException extends DukeException {
    public InvalidCommandException() {
        super("\n" + "Please use me properly dude! Do not ask me to do something I am not trained in. "
                + "Winter is coming.");
    }
}

