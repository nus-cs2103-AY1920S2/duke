package duke.exception;

public class InvalidCommandException extends DukeException {
    public InvalidCommandException(String errorMessage) {
        super(errorMessage);
    }
}