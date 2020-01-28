package duke.exception;

import duke.main.Constant;

public class NoDateException extends DukeException {

    @Override
    public String toString() {
        return Constant.ERROR_LINE + "\n    ☹ OI. Provide date of the deadline with /at! >:(\n"
                + Constant.ERROR_LINE;
    }
}