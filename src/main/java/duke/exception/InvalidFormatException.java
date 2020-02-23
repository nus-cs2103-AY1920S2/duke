package duke.exception;

/**
 * A parent class of all the exceptions due to user providing invalid formatted commands.
 */
public class InvalidFormatException extends DukeException {
    public InvalidFormatException() {
        super("\n" + "Your command is not following the right format mate. Winter is coming.");
    }
}
