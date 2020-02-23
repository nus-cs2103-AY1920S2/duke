package duke.exception;

@SuppressWarnings("serial")
public class DukeNoSuchInputException extends DukeException {
    public DukeNoSuchInputException() {
        super("No input was found!");
    }
}