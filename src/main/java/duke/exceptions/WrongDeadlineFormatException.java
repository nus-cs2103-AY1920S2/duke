package duke.exceptions;

public class WrongDeadlineFormatException extends DukeException {
    @Override
    public String toString() {
        return "Please enter your deadline in the following format.\n"
                + "Description /by YYYY-MM-DD";
    }
}
