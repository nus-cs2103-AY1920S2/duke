/**
 * Represents a Task to be managed/stored by the command line application Duke
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task with the given description and sets isDone to false by default
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon of this Task based on whether it is done or not
     * @return a String containing the status icon (tick-mark or cross)
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks this task as done by setting the isDone property to true
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Gives a string representation of the Task including the status icon
     * @return a String representation of the task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}