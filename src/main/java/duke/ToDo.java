package duke;

/**
 * Todo is a sub-class of Task. The only difference is the toString method.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo object.
     * @param description Description of ToDo.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of ToDo in the format [D][status] <description>.
     * @return String representation of ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
