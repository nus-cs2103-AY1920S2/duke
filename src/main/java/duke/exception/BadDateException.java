package duke.exception;

/**
 * Represents a bad date exception,
 * whereby the date format provided does not coincide with the appropriate format.
 */
public class BadDateException extends DukeException {
    public BadDateException(String errorMsg) {
        super(errorMsg);
    }
}
