package duke.exception;

/**
 * Exception to throw when the command is not known.
 */
public class UnknownCommandException extends DukeException {
    public UnknownCommandException(String command) {
        super("Sorry my guy, I don't know the command: '" + command + "'");
    }
}
