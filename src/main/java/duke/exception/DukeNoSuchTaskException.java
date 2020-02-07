package duke.exception;

/**
 * Signals an exception when there is no such task number.
 */
public class DukeNoSuchTaskException extends DukeException {
    @Override
    public String getMessage() {
        return "OOPS!!! Do you have this task number?";
    }
}
