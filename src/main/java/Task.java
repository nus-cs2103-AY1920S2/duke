/**
 * Represents a task. A <code>Task</code> object corresponds to a generic task represented by
 * task description and a boolean flag identifying if the task has been completed.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task() {}

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns status of task as an icon, represented in a string.
     *
     * @return Status of task as a string.
     */
    public String getStatusIcon() {
        return (isDone ? "OK!" : " X ");
    }

    /**
     * Returns task description.
     *
     * @return Task description.
     */
    public String getDescription() { return description; }

    /**
     * Returns status of task.
     *
     * @return Task status.
     */
    public boolean getIsDone() { return isDone; }

    /**
     * Change status of task to done.
     */
    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        String toReturn = "";
        if (this instanceof Todo) {
            toReturn = "[T][" + getStatusIcon() + "] " + description;
        } else if (this instanceof Event) {
            toReturn = "[E][" + getStatusIcon() + "] " + description + " (at: " + ((Event) this).getDayTime() + ")";
        } else if (this instanceof Deadline) {
            toReturn = "[D][" + getStatusIcon() + "] " + description + " (by: " + ((Deadline) this).getDayTime() + ")";
        }
        return toReturn;
    }
}