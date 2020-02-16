package duke.exceptions;

public class NoTasksScheduledException extends DukeException {
    @Override
    public String toString() {
        return "You do not have any tasks scheduled on this date.\n";
    }
}
