package task;

/**
 * This class creates Task object.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructor for creating new Task object.
     *
     * @param description This is the description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * This method retrieves the status icon for a Task.
     *
     * @return String of the icon.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Mark Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Check for done status of a Task.
     *
     * @return boolean This checks Task is done.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Gets description of a Task.
     *
     * @return String description of the Task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the String representation of Task.
     *
     * @return String of the Task.
     */
    public String toString() {
        return description;
    }
}