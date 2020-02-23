package duke.exception;

@SuppressWarnings("serial")
public class DukeNoCommandException extends DukeException {
    public DukeNoCommandException() {
        super("No command issued!");
    }
}