package duke.exception;

import duke.ui.UI;

public class DukeException extends Exception {

    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return UI.wrapper(String.format("%s", getMessage()));
    }

}
