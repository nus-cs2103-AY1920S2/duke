package duke.exceptions;

public class UnrecognizedCommandException extends DukeException {
    @Override
    public String toString() {
        return "Your command is not recognized";
    }
}
