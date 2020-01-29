package duke.exception;

import duke.ui.Ui;

/**
 * Duke exception class to handle exceptions in the Duke program.
 */
public class DukeException extends Exception {

    /**
     * Constructor for a Duke Exception.
     */
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return Ui.wrap(String.format("%s", getMessage()));
    }

}
