package tasks;

/**
 * Task without any date/time attached to it.
 */
public class Todo extends Task {

    /**
     * Creates a task without any date/time attached to it.
     *
     * @param description task to be completed.
     */
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, String tags) {
        super(description, tags);
    }

    /**
     * Returns the string of the task containing the description.
     *
     * @return the string of the task containing the description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}