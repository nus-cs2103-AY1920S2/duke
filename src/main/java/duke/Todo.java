package duke;

/**
 * The type Todo.
 */
public class Todo extends Task {

    /**
     * Instantiates a new Todo.
     *
     * @param description the description
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}