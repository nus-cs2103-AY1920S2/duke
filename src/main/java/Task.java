/**
 * Represent a Task in the TaskList.
 * A Task is described by its <code>String</code> description and
 * a <code>boolean</code> isDone to indicate whether a Task is completed.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "DONE" : "UNDONE");
    }

    /**
     * Sets the Task as Done.
     */
    public void markAsDone() {
        isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + this.description;
    }
}
