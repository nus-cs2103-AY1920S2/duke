package duke.task;

/**
 * Represents a task.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description The specified description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status icon that determine if a task is done (Y or N).
     *
     * @return Y if the task is done or N if the task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "Y" : "N"); //return Y or N symbols
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a string representation of the task that will be saved in storage.
     *
     * @return The string representation of the task that will be saved in storage.
     */
    public String toSaveName() {
        if (this.isDone) {
            return " | 1 | " + this.description;
        } else {
            return " | 0 | " + this.description;
        }
    }

    /**
     * Returns a string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + this.description;
    }

}

