package duke.exceptions;

public class UnknownTaskException extends DukeException {
    @Override
    public String toString() {
        return "Patrick you silly, this task number does not exist!\n";
    }
}
