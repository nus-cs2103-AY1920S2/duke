package duke.task;

/**
 * Represents a todo.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo with the specified description.
     *
     * @param description The specified description.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo that will be saved in storage.
     *
     * @return The string representation of the todo that will be saved in storage.
     */
    @Override
    public String toSaveName() {
        return "T" + super.toSaveName() + "\n";
    }

    /**
     * Returns a string representation of the todo.
     *
     * @return The string representation of the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
