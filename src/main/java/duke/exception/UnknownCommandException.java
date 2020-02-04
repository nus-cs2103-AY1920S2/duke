package duke.exception;

import duke.main.Constant;

public class UnknownCommandException extends DukeException {
    @Override
    public String toString() {
        return "☹ OI. Can you give me a proper command... >:(";
    }
}