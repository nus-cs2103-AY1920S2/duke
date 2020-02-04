package duke.exception;

import duke.main.Constant;

public class NoDescriptionException extends DukeException {

    @Override
    public String toString() {
        return "☹ OI. Provide the description of the task! >:(";
    }
}