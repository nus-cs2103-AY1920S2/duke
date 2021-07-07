package duke.task;

/**
 * Parent Task class.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon.
     *
     * @return Tick (V) if done, cross (X) if not done
     */
    public String getStatusIcon() {
        return (isDone ? "V" : "X");
    }

    /**
     * Marks a task as done by changing its isDone status.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Gets the status of the task.
     *
     * @return Status of the task
     */
    public boolean getStatus() {
        return isDone;
    }

    /**
     * Stringifies the Task object to a default string representation.
     *
     * @return Default string representation of a Task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
