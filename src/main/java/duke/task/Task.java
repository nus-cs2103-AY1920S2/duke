package duke.task;

/**
 * The duke.task.Task class represents a task, which has a description and whether it is completed or not.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Create a task from description, date and time provided.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get the status icon (done or not done) of the task.
     *
     * @return the status icon of the task
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Set the done status of the task to true or false.
     *
     * @param   isDone whether the task is done or not
     */
    public void markAsDone(Boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Get the done status of the task.
     *
     * @return the done status of the task.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Get the description of the task.
     *
     * @return the description of the task
     */
    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + description;
    }
}