package duke.exception;

import duke.main.Constant;

public class NoDateTimeException extends DukeException {

    @Override
    public String toString() {
        return Constant.ERROR_LINE + "\n    ☹ OI. Provide date and time of the event with /at! >:(\n"
                + Constant.ERROR_LINE;
    }
}