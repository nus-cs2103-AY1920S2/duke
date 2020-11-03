package duke.tasks;

/**
 * Represents a generic Task.
 */
public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", getStatus(), name);
    }
}