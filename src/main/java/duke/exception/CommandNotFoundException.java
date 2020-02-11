package duke.exception;

/**
 * Represents the exception when the command typed by the user cannot be found.
 */
public class CommandNotFoundException extends DukeException {
    /**
     * Constructs a CommandNotFoundException instance.
     * @param message The error message.
     */
    public CommandNotFoundException(String message) {
        super(message);
    }
}
