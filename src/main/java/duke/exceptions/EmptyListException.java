package duke.exceptions;

public class EmptyListException extends DukeException {
    @Override
    public String toString() {
        return "Your list of tasks is currently empty.";
    }
}
