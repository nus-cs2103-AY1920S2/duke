package duke.exception;

import duke.main.Constant;

public class UnableToLoadException extends DukeException {

    @Override
    public String toString() {
        return "☹ Unable to load. Can check the location and tell me the right location >:(";
    }
}
