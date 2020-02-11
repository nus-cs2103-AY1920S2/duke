package duke.exceptions;

public class MissingDetailsException extends DukeException {
    @Override
    public String toString() {
        return "Please enter more details with your command!";
    }
}
