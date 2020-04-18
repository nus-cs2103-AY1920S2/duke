package duke;

/**
 * A Task is something that needs to be done by the user, at anytime.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructor of a task.
     * @param description The information about the task that needs to be done.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets a status icon, a check or a cross based on the task's completion status.
     * @return An icon of a check or a cross.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2714" : "\u2718");
    }

    /**
     * A setter method for the task. Sets the task as done.
     */
    public void setDone() {
        assert !getStatus(): "task already done";
        this.isDone = true;
    }

    /**
     * A getter method to access the description of the task.
     * @return The description of the task, without the status icon.
     */
    public String getDescriptionWithoutIcon() {
        return this.description;
    }

    /**
     * A getter method to access the status of completion of the task
     * @return Boolean value to determine if the task has been completed.
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * A getter method to access the full description of the task
     * @return A string denotes the description of the task, including the status icon.
     */
    public String getDescription() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * A complete information of the task, with a T prefix to denote that this is a task.
     * @return A string representation of the task, with description and status icon.
     */
    public String toString() {
        return "[T]" + getDescription();
    }
}