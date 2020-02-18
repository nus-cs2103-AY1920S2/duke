package duke.exception;

/**
 * Signals an exception when there is no such command.
 */
public class DukeInvalidCommandException extends DukeException {
    @Override
    public String getMessage() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
