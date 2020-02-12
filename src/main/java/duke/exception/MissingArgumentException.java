package duke.exception;

/**
 * Indicates an exception when the argument is missing.
 */
public class MissingArgumentException extends DukeException {
    /**
     * Constructs a MissingArgumentException.
     * @param details The details that should not be empty.
     */
    public MissingArgumentException(String details) {
        super("The " + details + " cannot be empty.");
    }
}
