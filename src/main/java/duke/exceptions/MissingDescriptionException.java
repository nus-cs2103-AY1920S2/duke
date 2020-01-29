package duke.exceptions;

public class MissingDescriptionException extends DukeException {
    @Override
    public String toString() {
        return "☹ OOPS!!! The description cannot be empty.";
    }
}
