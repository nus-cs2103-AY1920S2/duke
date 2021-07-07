package dukeApp.files;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a tick if task is done and cross if otherwise
     * @return a tick or cross icon
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Mark task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    public abstract String getType();

    public abstract String getDescription();

    /**
     * Check if a task is done
     * @return 1 if done and 0 otherwise
     */
    public int getDone() {
        return isDone ? 1 : 0;
    }

    public abstract String storageFormat();
}