package duke.exception;

/**
 * Represents an invalid command,
 * whereby the command given is not found in the enum class: Command.
 */
public class InvalidCommandException extends DukeException {
    public InvalidCommandException (String errorMsg) {
        super(errorMsg);
    }
}
