package sharadhr.duke.exception;

/**
 * An exception thrown when the {@code detail} {@link String} passed to a
 * {@link sharadhr.duke.task.Task} constructor is empty.
 */
public class DukeEmptyDetailException extends DukeException {
    private static final long serialVersionUID = 1127957475772724808L;

    public DukeEmptyDetailException(String thrownBy) {
        super("Task detail cannot be empty.", thrownBy);
    }

    @Override public String toString() {
        return String.format("%s%nThrown by: %s", this.getMessage(), this.thrownBy);
    }
}