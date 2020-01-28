/**
 * Represents a Task to be completed, created by user.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Retrieves status icon based on whether task has been marked as done according to isDone.
     *
     * @return "Y" if task has been done, or "N" if it has not.
     */
    public String getStatusIcon() {
        return (isDone ? "Y" : "N"); //return tick or X symbols
    }

    /**
     * Mark isDone as true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return description;
    }
}