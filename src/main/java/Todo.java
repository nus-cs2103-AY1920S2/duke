/**
 * Represents a normal task with only a description.
 */
public class Todo extends Task {
    /**
     * Constructor that takes in the description of the task.
     * @param description the description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
