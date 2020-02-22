package duke.task;

/**
 * Represents an Todo task.
 */
public class Todo extends Task {
    /**
     * Constructs a new Todo task with description.
     *
     * @param description description of the task.
     */
    public Todo(String description) {
        super(description, false);
    }

    /**
     * Constructs a new Todo task with description and done status.
     *
     * @param description description of the task.
     * @param isDone done status of the task.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Gets the string representation of the todo task used for saving.
     *
     * @return the string representation of the event task to be used for saving.
     */
    @Override
    public String getSaveRepresentation() {
        return "T|||" + getIsDone() + "|||" + getDescription() + "\n";
    }

    /**
     * Gets the string representation of the todo task.
     *
     * @return the string representation of the todo task.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", "T", (getIsDone() ? "Y" : "N"), getDescription());
    }
}
