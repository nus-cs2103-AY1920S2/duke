package duke.task;

/**
 * Class representing a Todo task.
 */
public class Todo extends Task {
    /**
     * Creates a Todo object.
     *
     * @param task String containing what to do.
     */
    public Todo(String task) {
        super(task);
    }

    /**
     * Returns String representation of Todo object to print.
     *
     * @return String representation of the todo task to print.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns String representation of Todo object to save.
     *
     * @return String representation of the todo task to save.
     */
    @Override
    public java.lang.String toSaveString() {
        return String.format("%s || todo || %s", super.toSaveString(), this.description);
    }
}
