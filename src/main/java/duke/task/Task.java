package duke.task;

/**
 * Represents a Task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object.
     *
     * @param description the title of the task.
     */
    public Task(String description) {
        assert description != "" : "Description is empty.";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task object when loading from disk.
     *
     * @param description the title of the task.
     * @param isDone      whether the task has been completed.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public abstract String formatSavingName();

    /**
     * Gets the string representation of whether the task has been completed.
     *
     * @return [Y] if task is completed, [N] if task is not completed.
     */
    public String getStatusIcon() {
        return (isDone ? "[Y]" : "[N]");
    }

    /**
     * Marks a task as done.
     *
     * @return acknowledgement message by Duke.
     */
    public String markAsDone() {
        if (isDone) {
            return "     This task has already been completed! :)\n       " + this;
        }
        this.isDone = true;
        return "     Nice! I've marked this task as done:\n       " + this;
    }

    /**
     * Generates the String representation of Task.
     *
     * @return the string representation of Task.
     */
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}