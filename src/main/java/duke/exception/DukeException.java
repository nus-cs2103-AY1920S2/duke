package duke.exception;

import duke.ui.Ui;

public class DukeException extends Exception {

    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return Ui.wrap(String.format("%s", getMessage()));
    }

}
