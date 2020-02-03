package duke;

/**
 * One of the tasks that could be created by user.
 */
public class Todo extends Task {

    /**
     * Todo constructor.
     *
     * @param description Describes the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Overwritten toString to fit format requirements.
     *
     * @return Formatted string.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}