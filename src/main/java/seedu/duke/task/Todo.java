package seedu.duke.task;

/**
 * Represents a Todo object.
 */
public class Todo extends Task {
    /**
     * Represents a Todo object.
     *
     * @param description Details of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public int compareTo(Task task) {
        return toString().compareTo(task.toString());
    }
}
