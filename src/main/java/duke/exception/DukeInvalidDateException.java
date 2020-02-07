package duke.exception;

/**
 * Signals error when date input is invalid.
 */
public class DukeInvalidDateException extends DukeException {
    @Override
    public String getMessage() {
        return "OOPS!!! Please give me the date in yyyy-mm-dd format!";
    }
}
