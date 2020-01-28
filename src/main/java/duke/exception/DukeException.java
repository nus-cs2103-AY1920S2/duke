package duke.exception;

import duke.ui.UI;

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
        return UI.wrapper(String.format("%s", getMessage()));
    }

}
