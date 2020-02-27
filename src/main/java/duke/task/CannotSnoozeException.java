package duke.task;

import duke.DukeException;

/**
 * Thrown when an attempt to snooze a task is unsuccesful.
 */
public class CannotSnoozeException extends DukeException {
    /**
     * Constructs a {@code CannotSnoozeException} with the specified detail message.
     *
     * @param message the detail message
     */
    public CannotSnoozeException(String message) {
        super(message);
    }
}
