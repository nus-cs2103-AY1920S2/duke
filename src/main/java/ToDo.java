/**
 * Represents a to do task.
 */
public class ToDo extends Task {

    /**
     * Constructor of to do object
     * @param description description of to do task
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
