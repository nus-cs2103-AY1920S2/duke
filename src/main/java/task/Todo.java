package task;

public class Todo extends Task {

    /**
     * Constructor for creating new Todo object.
     *
     * @param description This is the description of the Todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the String representation of Todo.
     *
     * @return String of the Todo.
     */
    @Override
    public String toString() {
        return "[T][" + super.getStatusIcon() + "] " + super.toString();
    }
}