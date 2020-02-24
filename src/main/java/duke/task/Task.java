package duke.task;

/**
 * Represents a task. A <code>Task</code> object corresponds to a task e.g.,
 * <code>"project meeting"</code>
 */

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    /**
     * Returns the text representation of whether a task is marked as done.
     * @return The text representation of whether a task is done or not.
     */

    public String getStatusIcon() {
        return (isDone ? "[Done] " : "[Not Done] "); //return tick or X symbols
    }
    /**
     * Returns the text representation of the binary of whether a task is done or not.
     * @return The text representation of the binary of whether a task is done or not.
     */

    public String getStatusIconInBin() {
        return (isDone ? "1" : "0"); //1 means done, 0 means not done
    }
    /**
     * Marks a task as done.
     */

    public void markDone() {
        this.isDone = true;
    }
    /**
     * Returns the text representation of the task.
     * @return The text representation of the task.
     */

    public abstract String toStringTasks();
    /**
     * Returns the status and the description of the task.
     * @return The status and description of the task.
     */

    @Override
    public String toString() {
        return getStatusIcon() + description;
    }
}
