package duke.task;

/**
 * Represents a Task.
 * It has a description, and is marked as done or not.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task instance.
     * @param description The description of the task.
     * @param isDone Whether the task is done or not.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the description of the task.
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns whether the task is marked as done.
     * @return A boolean marking the task as done or not.
     */
    public String getIsDone() {
        if (this.isDone) {
            return "[O] ";
        } else {
            return "[X] ";
        }
    }

    /**
     * Sets a new description for the task.
     * @param newDescription The new description specified by the user.
     */
    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * Sets a new time for the event task.
     * @param newTime The new time specified by the user.
     */
    public abstract void setTime(String newTime);

    /**
     * Sets the task as done.
     * @param isDone A boolean marking the task as done or not.
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
}