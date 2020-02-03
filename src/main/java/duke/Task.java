package duke;

/**
 * Represent a task to be done by the user.
 * Described by a string and boolean to indicate whether its done.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs the task. Tasks are not done by default.
     *
     * @param description What the task is about.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves the tick or X symbol.
     * A tick if it is done and a X if it is not.
     *
     * @return Returns as a string.
     */
    public String getStatusIcon() {
        return (isDone ? "[✓] " : "[✘] ");
    }

    /**
     * Marks the task as done.
     * Throws exception if task is already done.
     */
    public void markAsDone() throws DukeException {
        if (isDone) {
            throw new DukeException("☹ OOPS!!! Task is already done!");
        } else {
            isDone = true;
        }
    }

    public String getDescription() {
        return description;
    }
    
    /**
     * Concatenate the status icon with the description.
     */
    @Override
    public String toString() {
        return getStatusIcon() + description;
    }
}