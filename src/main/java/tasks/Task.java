package tasks;

/**
 * Specify main attributes and methods for tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Create a task object with a description.
     *
     * @param description of the task.
     */
    public Task(String description) {
        System.out.println("\tGot it. I've added this task:");
        this.description = description;
        this.isDone = false;
    }

    /**
     * Return description of the task.
     * @return task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Return a tick for tasks marked as done and a cross for tasks not done.
     *
     * @returna tick for tasks marked as done and a cross for tasks not done.
     */

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Change the status of the task to done.
     */
    public void setDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
