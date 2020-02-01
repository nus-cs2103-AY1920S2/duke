package duke.task;

/**
 * A basic extension of Task class, with no added parameters
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo with description
     * @param description Desription of ToDo
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a String representation of the Task object
     * @return a String representation of the Task object
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}