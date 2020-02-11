package duke.exception;

import duke.exception.DukeException;

public class OutOfRangeException extends DukeException {
    public OutOfRangeException() {
        super("☹ OOPS!!! Task number out of range!");
    }
}