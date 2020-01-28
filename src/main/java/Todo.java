
/**
 * Represents a <code>Todo</code>, which is a subclass of a <code>Task</code>. A Todo only contains a description.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean done) {
        super(description, done);
    }

    @Override
    public String toString() {
        String checkbox = "[" + super.getStatusIcon() + "]";
        return "[T]" + checkbox + " " + super.toString();
    }
}