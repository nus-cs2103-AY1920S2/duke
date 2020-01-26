/**
 * Represents a To-do which inherits from Task and is stored/managed by Duke
 */
public class ToDo extends Task {
    /**
     * Creates a To-Do object with given description.
     * @param description
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Gives a string representation of the To-do by building upon parent's representation method
     * @return a string representation
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
