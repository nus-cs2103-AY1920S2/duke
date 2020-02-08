/**
 * Represents a Task to be completed, created by user.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        assert !description.isEmpty();
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
     * Retrieves done status of the task.
     *
     * @return Boolean representing whether task has been marked as done.
     */
    public Boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Mark isDone as true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return description;
    }
}