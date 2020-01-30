/**
 * The simple implementation of Task class.
 */
public class Todo extends Task {

    /**
     * Constructs a new instance of Todo.
     *
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
