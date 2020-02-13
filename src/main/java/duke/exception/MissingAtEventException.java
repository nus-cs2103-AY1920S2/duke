package duke.exception;

public class MissingAtEventException extends DukeException {

    @Override
    public String toString() {
        return "☹ OOPS!!! Remember to use \"/at\" for Events.";
    }
}
