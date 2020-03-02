package duke.command.exception;

/**
 * Represents an error encountered when adding a duplicate task into Duke.
 */
public class DuplicateTaskException extends CommandException {
    /**
     * Constructs a new {@code DuplicateTaskException} when adding a duplicate task into Duke.
     */
    public DuplicateTaskException() {
        super("Don't make me remember the same thing!");
    }
}
