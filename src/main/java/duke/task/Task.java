package duke.task;

/**
 * Represents a Task.
 */
public class Task {
    /** The description of the task. */
    private String description = "";
    /** The done status of the task. */
    private boolean isDone = false;

    /**
     * Construct a new Task.
     *
     * @param description description of the task.
     * @param isDone is the task done.
     */
    public Task (String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Gets the description of the task.
     *
     * @return the description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets whether the task is done.
     *
     * @return true if the tasks is done, false otherwise.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Gets the string representation of the task used for saving.
     *
     * @return a string representation of the task to be used for saving.
     */
    public String getSaveRepresentation() {
        return isDone + description;
    }

    /**
     * Gets the string representation of the task.
     *
     * @return the string representation of the task.
     */
    public String toString() {
        return String.format("[%s] %s", (isDone ? "\u2713" : "\u2718"), description);
    }
}
