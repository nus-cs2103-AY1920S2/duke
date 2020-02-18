package duke.task;

/**
 * A task in Duke.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status of the task.
     * @return A tick if task is done, a cross if task is not done.
     */
    private String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Sets the task to be done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Returns whether the task is done.
     * @return true if task is done, false if task is not done.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the description of a task.
     * @return Description of task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Set the description of a task.
     * @param description Description of task.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the type of the task.
     * @return Type of task.
     */
    public abstract String getType();

    public abstract Task getCopy();

    @Override
    public String toString() {
        return '[' + this.getType() + "][" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
