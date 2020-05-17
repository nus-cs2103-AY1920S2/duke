package duke.exceptions;

public class MissingDetailsException extends DukeException {
    @Override
    public String toString() {
        return "Patrick, enter more details with your command!\n";
    }
}
