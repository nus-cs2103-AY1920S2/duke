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
        System.out.println("\t\t" + this);
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