@SuppressWarnings("serial")
public class DukeUnrecognisedCommandException extends DukeException {
    public DukeUnrecognisedCommandException(String command) {
        super(String.format("Unrecognised command: %s", command));
    }
}