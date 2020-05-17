package duke.exceptions;

public class WrongDeadlineFormatException extends DukeException {
    @Override
    public String toString() {
        return "Patrick, your deadline needs to be in this format:\n"
                + "Description /by YYYY-MM-DD\n";
    }
}
