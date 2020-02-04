package duke.exception;

import duke.main.Constant;

public class NoDateException extends DukeException {

    @Override
    public String toString() {
        return "☹ OI. Provide date of the deadline with /at! >:(" ;
    }
}