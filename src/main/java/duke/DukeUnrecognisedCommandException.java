package duke;

@SuppressWarnings("serial")
public class DukeUnrecognisedCommandException extends DukeException {
    public DukeUnrecognisedCommandException(String commandName) {
        super(String.format("Unrecognised command: %s", commandName));
    }
}