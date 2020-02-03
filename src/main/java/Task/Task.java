package task;

/**
 * defines Task class with basic properties and is set to be not done initally.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * constructs a Task instance by its description.
     * @param description a string represents the Task's description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * marks the Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * gets the integer which represents the status of the Task.
     * @return "1" if the Task is done or "0" otherwise.
     */
    public String getStatus() {
        return (isDone ? "1" : "0");
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
