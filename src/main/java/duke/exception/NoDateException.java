package duke.exception;

public class NoDateException extends DukeException {

    @Override
    public String toString() {
        return "☹ OI. Provide date of the deadline with /by! >:(";
    }
}