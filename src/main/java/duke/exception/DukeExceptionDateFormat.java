package duke.exception;

/**
 * Generates an error message for the situation where a date was not given in the yyyy-mm-dd format.
 */
public class DukeExceptionDateFormat extends DukeException {
    public DukeExceptionDateFormat() {
        super.errorMsg = "The dates need to be in yyyy-mm-dd format.";
    }
}
