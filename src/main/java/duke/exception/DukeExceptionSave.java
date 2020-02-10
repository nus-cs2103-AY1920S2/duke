package duke.exception;

/**
 * Generates an error message for the situation where the txt file cannot be written to.
 */

public class DukeExceptionSave extends DukeException {
    public DukeExceptionSave() {
        super.errorMsg = "I... cant remember the tasks on the list...";
    }
}