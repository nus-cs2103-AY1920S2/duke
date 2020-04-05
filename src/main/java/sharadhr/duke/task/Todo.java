package sharadhr.duke.task;

import sharadhr.duke.exception.DukeEmptyDetailException;

/**
 * A To-Do; effectively the same as the inherited abstract class, {@link Task}.
 */
public class Todo extends Task {

    /**
     * Creates a Todo with some detail.
     *
     * @param detail the Todo detail
     * @throws DukeEmptyDetailException if the detail is blank
     */
    public Todo(String detail) throws DukeEmptyDetailException {
        super(detail);
    }

    public Todo(String detail, boolean isComplete) throws DukeEmptyDetailException {
        super(detail, isComplete);
    }

    @Override public char getTaskTypeIcon() {
        return 'T';
    }

    @Override public String toString() {
        return String.format("[%c]%s", this.getTaskTypeIcon(), super.toString());
    }

    @Override public String encode() {
        return String
            .format("%c,%d,%s", this.getTaskTypeIcon(), this.isComplete ? 1 : 0, this.detail);
    }
}