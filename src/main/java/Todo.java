/**
 * Todo class that is a type of Task.
 */
public class Todo extends Task {
    /**
     * Creates a Todo Task object.
     *
     * @param description A string representation of the task description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Gets the task description, time and status whether is it done.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}