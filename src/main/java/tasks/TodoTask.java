package tasks;

/**
 * Todo Task object, contains methods pertaining to the Todo Task.
 */
public class TodoTask extends Task {
    /**
     * Constructor of a todo task.
     *
     * @param description Description of the todo task.
     */
    public TodoTask(String description) {
        super(description);
    }

    /**
     * Returns the Todo task formatted with the task type and whether it is done or not.
     *
     * @return Todo task in String form.
     */
    @Override
    public String toString() {
        return String.format("%s%s", "[T]", super.toString());
    }
}
