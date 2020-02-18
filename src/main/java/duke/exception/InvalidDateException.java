package duke.exception;

/**
 * An exception thrown when an invalid date is entered.
 */
public class InvalidDateException extends DukeException {
    @Override
    public String getMessage() {
        return "Invalid date entered. Please enter the date in the form YYYY-MM-DD";
    }
}
