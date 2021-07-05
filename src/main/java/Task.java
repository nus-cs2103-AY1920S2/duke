/**
 * Represents the parent class of ttodo, Deadline and Event.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns status icon: tick if done and cross if not done.
     * @return icon
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Sets flag isDone to true to indicate completion of task.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns description of task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns task in string format with a date.
     * @return string format to save in storage.
     */
    public String saveFormat() {
        return "," + this.isDone + "," + getDescription();
    }

    /**
     * Returns status of task.
     * @return true if done, false if incomplete.
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Return task in string format.
     * @return string format to show user.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + getDescription();
    }
}