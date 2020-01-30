/**
 * Represents a template for tasks, with a description and
 * an indicator for whether it is completed.
 */
public class Task {
    /**
     * The description of the task.
     */
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task that takes in the description of the task.
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a status icon indicating the task completion status.
     * @return a check if the task is completed, or a cross if it is not completed
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks the task as completed.
     */
    public void tick() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String encode() {
        return "";
    }
}
