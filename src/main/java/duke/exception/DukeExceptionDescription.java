package duke.exception;

/**
 * Generates an error message for the situation where the description of the task was not given.
 */
public class DukeExceptionDescription extends DukeException {
    public DukeExceptionDescription(String task) {
        super.errorMsg = "More details for the " + task + " please?";
    }
}
