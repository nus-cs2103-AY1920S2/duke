package liaomeng.duke;

/**
 * A task without a specified due date or occurring date.
 */
public class Todo extends Task {
    /**
     * Creates a todo task.
     *
     * @param isDone boolean indicating whether the task is marked as done.
     * @param description string description of the task.
     * @param level priority level of the task.
     */
    public Todo(boolean isDone, String description, PriorityLevel level) {
        super(isDone, description, level);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSimplerString() {
        return "T//" + super.toSimplerString();
    }
}
