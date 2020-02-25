import java.util.InvalidPropertiesFormatException;

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
    protected boolean isSnoozeable;

    /**
     * String of class name for JSON compatibility.
     */
    String type;

    /**
     * Returns String of class name for JSON compatibility.
     *
     * @return String of class name
     */
    protected String getType() {
        return type;
    }

    /**
     * Constructor for Task that takes in the description of the task.
     *
     * @param description the description of the task.
     */
    public Task(String description) {
        this.type = "Task";
        this.description = description;
        this.isDone = false;
        this.isSnoozeable = false;
    }

    /**
     * Returns a status icon indicating the task completion status.
     *
     * @return a check if the task is completed, or a cross if it is not completed
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public boolean isSnoozeable() {
        return isSnoozeable;
    }

    /**
     * Returns a snoozed version of task.
     *
     * @param time the time after snoozing
     * @return the snoozed version of task
     */
    public Task snooze(String time) throws InvalidPropertiesFormatException {
        // not snoozeable
        return null;
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
}
