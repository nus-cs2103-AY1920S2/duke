package duke.task;

/**
 * Represents a Task that has no deadline.
 */
public class Todo extends Task {
    public Todo(String identifier) {
        super(identifier);
    }

    public String toString() {
        return "Todo: " + super.toString();
    }
}
