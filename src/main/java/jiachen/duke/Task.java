package jiachen.duke;

/**
 * generic abstract task object that specific kinds of task can implement.
 */
public abstract class Task {
    /**
     * The Description.
     */
    protected String description;
    /**
     * Is task completed?.
     */
    protected boolean isDone;

    /**
     * Instantiates a new Task.
     *
     * @param description the description
     */
    public Task(String description) throws InvalidDukeFormatException {
        this.description = description;
        this.isDone = false;

        if (description.equals("")) {
            throw new InvalidDukeFormatException("description cannot be empty.");
        }
    }

    /**
     * Gets status icon.
     *
     * @return the status icon
     */
    public String getStatusIcon() {
        return isDone ? "\u2713" : "\u2718";
    }

    /**
     * Mark as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Format string.
     *
     * @return the string
     */
    public String format() {
        return (isDone ? "1 | " : "0 | ") + this.description;
    }
}
