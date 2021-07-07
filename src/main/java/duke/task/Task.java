package duke.task;

/**
 * The Class Task is thr root for all different type of tasks.
 */
public class Task {

    /**
     * The description: describes the task.
     */
    protected String description;

    /**
     * The is done: indicates if the task is being completed.
     */
    protected boolean isDone;

    /**
     * Instantiates a new task.
     *
     * @param description the description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon : is it completed.
     *
     * @return the status icon
     */
    public String getStatusIcon() {
        //return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
        return (isDone ? "tick" : "X"); //return tick or X symbols
    }

    /**
     * Mark the task to be done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark the task to be undone.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Gets the description.
     *
     * @return the description of the task (e.g: Buy food)
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the status.
     *
     * @return a boolean: true: the task is done, false: the task is not done
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
