/**
 * Represent a task to do.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task to do.
     * @param description of the activity
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a tick or cross icon depending on the task completion status.
     * @return String representing a tick or cross
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Returns a description of the task.
     * @return String of the task
     */
    public String getTask() {
        return this.description;
    }

    /**
     * Returns the completion status of the task.
     * @return 1 for true 0 for false
     */
    public String checkDone() {
        return (this.isDone ? "1" : "0");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

}
