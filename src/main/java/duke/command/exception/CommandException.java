package duke.command.exception;

import duke.DukeException;

/**
 * Represents an error encountered when executing a command.
 */
public class CommandException extends DukeException {
    /**
     * Constructs a new {@code CommandException} when executing a command.
     *
     * @param message the error message.
     */
    public CommandException(String message) {
        super(message);
    }
}
