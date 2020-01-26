package duke.exception;

@SuppressWarnings("serial")
public class DukeNoArgumentsException extends DukeException {
    public DukeNoArgumentsException(String command) {
        super(String.format("No arguments received for %s", command));
    }
}

