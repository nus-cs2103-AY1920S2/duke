package duke.exception;

public class UnknownTaskException extends DukeException {

    @Override
    public String toString() {
        return "☹ OOPS!!! I'm sorry, the Task Number entered does not exist.";
    }
}
