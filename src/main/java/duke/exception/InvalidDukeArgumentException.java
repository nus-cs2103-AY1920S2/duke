package duke.exception;

/**
 * Represents the exception when the commands receive invalid arguments such as not a number or the number is
 * out of range.
 */
public class InvalidDukeArgumentException extends DukeException {
    /**
     * Constructs a InvalidArgumentException.
     * @param message The error message.
     */
    public InvalidDukeArgumentException(String message) {
        super(message);
    }
}
