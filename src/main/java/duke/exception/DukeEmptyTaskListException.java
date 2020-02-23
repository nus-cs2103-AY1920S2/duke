package duke.exception;

@SuppressWarnings("serial")
public class DukeEmptyTaskListException extends DukeException {
    public DukeEmptyTaskListException() {
        super("Cannot get task from empty task list!");
    }
}