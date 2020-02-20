package duke.task;

import duke.DukeException;

/**
 * CannotSnoozeException is an exception that is raised when an attempt is made
 * to snooze a task that does not implement the Snoozable interface.
 */
public class CannotSnoozeException extends DukeException {
    /**
     * Instantiates a CannotSnoozeException with a message.
     *
     * @param message the message for the exception
     */
    public CannotSnoozeException(String message) {
        super(message);
    }
}
