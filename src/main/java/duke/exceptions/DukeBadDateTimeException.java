package duke.exceptions;

/**
 * Handles qdate and time inputs that are not formatted correctly.
 */
public class DukeBadDateTimeException extends DukeException {
    public DukeBadDateTimeException() {
        super.message = "The date and time you entered is in a bad format!\n\tPlease use the format YYYY-MM-DD HHMM.";
    }
}
