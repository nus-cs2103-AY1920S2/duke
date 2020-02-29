package duke.exceptions;

import duke.util.Ui;

public class DukeException extends Exception {
    protected String message;

    public DukeException(String s) {
        this.message = s;
    }

    @Override
    public String toString() {
        return Ui.setBorder(message);
    }
}
