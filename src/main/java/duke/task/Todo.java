package duke.task;

/**
 * Represents a Todo task in the application.
 * A Todo task consist of a description.
 */
public class Todo extends Task {
    /**
     * Constructs a new Todo task with the specified description.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the serialized form of this Todo task in the form of a string.
     *
     * @return A string that is the serialized form of this Todo task.
     */
    @Override
    public String serialize() {
        return String.format("T,%d,%s,", isDone ? 1 : 0, description);
    }

    /**
     * Returns the string representation of this Todo task.
     *
     * @return The string representation of this Todo task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
