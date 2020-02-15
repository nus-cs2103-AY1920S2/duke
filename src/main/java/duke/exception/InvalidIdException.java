package duke.exception;

/**
 * Indicates an exception when the ID is invalid.
 */
public class InvalidIdException extends DukeException {
    /**
     * Constructs an InvalidIdException.
     * @param type The type of command.
     */
    public InvalidIdException(String type) {
        super("The ID of the task " + type + " should be a number.");
    }
}
