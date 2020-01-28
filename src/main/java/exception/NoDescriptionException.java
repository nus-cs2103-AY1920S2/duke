package exception;

import main.Constant;

public class NoDescriptionException extends DukeException {

    @Override
    public String toString() {
        return Constant.ERROR_LINE + "\n    ☹ OI. Provide the description of the task! >:(\n"
                + Constant.ERROR_LINE;
    }
}