package duke;

/**
 * Todo class that inherits from Task class.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * To string method for Todo.
     * @return string for toString method
     */
    @Override
    public String toString() {
        return "[T]" + "[" + super.getStatusIcon() + "] "  + super.toString();
    }
}
