package exception;

import main.Constant;

public class UnableToLoadException extends DukeException {

    @Override
    public String toString() {
        return Constant.ERROR_LINE + "\n    ☹ Unable to load. Can check the location and tell me the right location >:(\n" + Constant.ERROR_LINE;
    }
}
