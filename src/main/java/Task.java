/**
 * Represents a task
 */

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new task
     * @param description This is the description of the task
     */

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the current task as done
     */

    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the status icon of the task
     * @return Returns tick if done or cross if not done
     */

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Returns the description of the current task
     * @return The description of the current task
     */

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status of the current task
     * @return Returns 1 if done or 0 if not done
     */

    public String getDone() {
        return (isDone ? "1" : "0");
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
