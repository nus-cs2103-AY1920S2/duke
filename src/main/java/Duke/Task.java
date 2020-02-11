package duke;

/**
 * Task class.
 */
public class Task implements java.io.Serializable {
    protected String description;
    protected boolean isDone;

    /**
     * Task constructor.
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Method for returning the status icon.
     * @return returns a status icon in string
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Change isDone to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Getter method for description.
     * @return returns a description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * toString method for task.
     * @return the toString statement
     */
    @Override
    public String toString() {
        return this.description;
    }
}
