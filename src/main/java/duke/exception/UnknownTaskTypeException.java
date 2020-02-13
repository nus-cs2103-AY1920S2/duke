package duke.exception;

public class UnknownTaskTypeException extends DukeException {

    @Override
    public String toString() {
        return "☹ OOPS!!! Please use only: E, D or T for TaskTypes!";
    }
}
