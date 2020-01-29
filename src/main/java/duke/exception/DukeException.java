package duke.exception;

import duke.ui.Ui;

/** Duke exception class to handle exceptions in the Duke program. */
public class DukeException extends Exception {

    /**
     * Constructs a Duke Exception.
     *
     * @oaram message Message to be shown to user upon exception handling.
     */
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return Ui.wrap(String.format("%s", getMessage()));
    }

}
