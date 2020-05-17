package duke.exceptions;

public class NoTasksScheduledException extends DukeException {
    @Override
    public String toString() {
        return "Patrick, you do not have any tasks scheduled on this date!\n"
                + "Want to catch some jellyfishes instead?\n";
    }
}
