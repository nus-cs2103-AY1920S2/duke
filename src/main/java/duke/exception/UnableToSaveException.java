package duke.exception;

import duke.main.Constant;

public class UnableToSaveException extends DukeException {

    @Override
    public String toString() {
        return Constant.ERROR_LINE + "\n    ☹ I CAN'T SAVE! Are you sure it's the right location\n"
                + Constant.ERROR_LINE;
    }
}
