package duke.task;

import duke.DukeException;

public class CannotSnoozeException extends DukeException {
    public CannotSnoozeException(String message) {
        super(message);
    }
}
