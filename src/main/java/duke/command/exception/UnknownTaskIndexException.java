package duke.command.exception;

/**
 * Represents an error encountered when searching for a task with an unknown id.
 */
public class UnknownTaskIndexException extends CommandException {
    /**
     * Constructs a new {@code UnknownTaskIndexException} when searching
     * for a task with an unknown id.
     */
    public UnknownTaskIndexException() {
        super("I can't find that task number!");
    }
}
