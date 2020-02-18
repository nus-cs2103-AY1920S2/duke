package duke.exception;

/**
 * Thrown when an error occurs due to invalid date and/or time.
 * It is also thrown when date and/or time format is wrong.
 */
public class DukeInvalidDateTimeException extends DukeException {

    public DukeInvalidDateTimeException() {
        super(Messages.MESSAGE_INVALID_DATE_TIME);
    }
}
