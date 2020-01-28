package duke;

/**
 * Class to represent tasks of the type to do.
 */
public class Todo extends Task {
    /**
     * Constructor method.
     * @param description description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Overloaded constructor in case of specified isDone value.
     * @param description description
     * @param mark mark
     */
    public Todo(String description, boolean mark) {
        super(description, mark);
    }

    /**
     * Custom toString implementation.
     * @return String
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Override saveFormat method to generate neatly formatted information for saving.
     * @return String
     */
    @Override
    public String saveFormat() {
        return "T" + " , " + (super.isDone ? "1" : "0") + " , " + super.description;
    }
}
