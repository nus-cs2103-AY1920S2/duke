package duke.task;

import java.io.Serializable;

/**
 * Represents the parent class of various types of tasks that the user inputs.
 */
public class Task implements Serializable {
    protected String identifier;
    protected boolean isCompleted;

    public Task(String identifier) {
        this.identifier = identifier;
        this.isCompleted = false;
    }

    /**
     * Gets the status of a Task.
     * @return Returns the status of completion of a task.
     */
    public String getStatusIcon() {
        return (isCompleted ? "Done" : "Pending");
    }

    /**
     * Sets the status to complete.
     * @return Returns true to signal completed status.
     */
    public boolean completeStatus() {
        this.isCompleted = true;
        return true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.identifier;
    }
}
