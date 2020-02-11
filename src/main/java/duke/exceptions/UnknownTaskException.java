package duke.exceptions;

public class UnknownTaskException extends DukeException {
    @Override
    public String toString() {
        return "This task number does not exist.";
    }
}
