package duke.exception;

import duke.exception.DukeException;

public class InsufficientArgumentsException extends DukeException {
    public InsufficientArgumentsException(String errMsg) {
        super(errMsg);
    }
}