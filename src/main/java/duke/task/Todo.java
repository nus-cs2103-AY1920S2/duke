package duke.task;

/**
 * Task object of type To-do.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Stringifies the To-do object to a default string representation.
     *
     * @return Default string representation of the To-do object
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
