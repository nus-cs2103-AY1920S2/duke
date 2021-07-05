/**
 * Task class that represents things to be done.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task object.
     *
     * @param description A string representation of the task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns symbol representing whether the task is done or not done.
     *
     * @return A string that is either tick or X.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = !this.isDone;
    }

    /**
     * Returns the task description.
     *
     * @return A string representation of the task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the task description and an symbol depending on whether it is done or not done.
     *
     * @return A string representation of the task description.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}