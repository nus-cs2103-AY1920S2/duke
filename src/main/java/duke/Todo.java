package duke;

/**
 * Represents a Todo Task the user would record.
 */
public class Todo extends Task {
    /**
     * Constructs a new Todo object.
     *
     * @param name Name of Todo Task.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Constructs a new Todo object.
     *
     * @param name Name of Todo Task.
     * @param isDone Done status of the Todo task.
     */
    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String getMnemonic() {
        return "T";
    }

    @Override
    public String toString() {
        return "[" + getMnemonic() + "]" + super.toString();
    }
}
