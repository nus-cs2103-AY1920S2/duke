package duke.exception;

/**
 * Indicates an exception due to an unknown command.
 */
public class UnknownCommandException extends DukeException {
    /**
     * Constructs an UnknownCommandException.
     */
    public UnknownCommandException() {
        super("I'm sorry, but I don't know what that means :-(");
    }
}
