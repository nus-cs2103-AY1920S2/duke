import duke.commons.exceptions.DukeException;

public class DuplicateTaskException extends DukeException {
    public DuplicateTaskException(String message) {
        super(message);
    }
}
