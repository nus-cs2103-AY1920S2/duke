package duke.exception;

/**
 * Represents a bad description exception,
 * whereby the description provided does not coincide with the appropriate format.
 */
public class BadDescriptionException extends DukeException {
    public BadDescriptionException (String errorMsg) {
        super(errorMsg);
    }
}
