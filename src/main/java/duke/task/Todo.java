package duke.task;

/**
 * {@code Todo} is a subclass of {@code Task}. It represents a basic task with a description and completion status.
 */
public class Todo extends Task {
    /**
     * Constructs a {@code Todo} object with the specified description, marked as incomplete.
     *
     * @param description the description of the todo
     */
    public Todo(String description) {
        super(description, false);
    }

    /**
     * Constructs a {@code Todo} object with the specified description and completion status.
     *
     * @param description the description of the todo
     * @param isCompleted the completion status of the todo
     */
    public Todo(String description, boolean isCompleted) {
        super(description, isCompleted);
    }

    @Override
    public Todo complete() {
        return new Todo(description, true);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
