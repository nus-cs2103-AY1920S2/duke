package tasks;

/**
 * Specifies main attributes and methods for tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task object with a description.
     *
     * @param description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return task description.
     */
    public String getDescription() {
        return description;
    }


    /**
     * Returns a tick for tasks marked as done and a cross for tasks not done.
     *
     * @return tick for tasks marked as done and a cross for tasks not done.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Changes the status of the task to done.
     */
    public void setDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
