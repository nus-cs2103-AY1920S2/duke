package hakunamatata.task;

/**
 * Represents a todo task.
 *
 * @author Wang Yuting
 */
public class ToDo extends Task {

    /**
     * Creates a new <code>Event</code> with the given <code>description</code>.
     */
    public ToDo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Creates a new <code>Event</code> with the given <code>description</code> and <code>isDone</code>.
     */
    public ToDo(String description, boolean isDone) {
        super(description, TaskType.TODO);
        this.isDone = isDone;
    }

    /**
     * Converts the <code>ToDo</code> detail into a String.
     *
     * @return The detail of <code>ToDo</code> in String.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
