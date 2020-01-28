package duke.task;

/**
 * Represents a Todo.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo with the specified description.
     * @param description The description of the Todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a Todo with the specified description and status.
     * @param description The description of the Todo.
     * @param isDone Whether the Todo is done.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation of the todo for saving to the disk.
     * @return String representation of the todo for saving to the disk.
     */
    @Override
    public String formatToSave() {
        return "T" + super.formatToSave();
    }

    /**
     * Returns a string representation of the todo for printing.
     * @return String representation of the todo for printing.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
