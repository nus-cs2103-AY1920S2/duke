package duke.task;

/**
 * Base class for Tasks
 */
public class Task implements java.io.Serializable {
    /**
     * Description of Task
     */
    protected String description;
    /**
     * Boolean to indicate true if task is Done
     */
    protected boolean isDone;

    /**
     * Constructor of Task class, creates a Task object with given description
     * @param description String description of Task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Helper method to get Task status icon depending on whether task is done or not.
     * @return String output
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks the current Task as done
     * @return True if success
     */
    public boolean markAsDone() {
        this.isDone = true;
        return true;
    }

    /**
     * Returns the description of this Task
     * @return The description of this Task
     */
    public String getDescription() {
        return description;
    }
  
    /**
     * Returns a String representation of the Task object
     * @return a String representation of the Task object
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}