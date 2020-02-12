/**
 * Represents a task. A <code>Task</code> object
 * corresponds to a task represented by a description and isDone boolean e.g.,
 * <code> "read book"</code>
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     *Constructor for task.
     * @param description description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets description of task.
     * @return description of task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Changes description of task.
     * @param description description to be changed to
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets boolean isDone
     * @return boolean isDone
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Sets isDone to true.
     */
    public void setIsDone() {
        isDone = true;
    }

    /**
     * Sets isDone to false.
     */
    public void setUnDone() {
        isDone = false;
    }

    /**
     * Gets icon for task status.
     * @return icon in string
     */
    public String getStatusIcon() {
        if (isDone) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
