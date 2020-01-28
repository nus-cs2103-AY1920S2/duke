package duke.exception;

/**
 * Represents a description exception, whereby the description is empty.
 */
public class DukeDescriptionException extends DukeException {
    public DukeDescriptionException(String errorMsg) {
        super(errorMsg);
    }
}
