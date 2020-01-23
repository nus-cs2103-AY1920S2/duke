/**
 * This is a simulation of tasks.
 */
public abstract class Task {
    /** Description of this task */
    protected String description;
    /** Status of this task, whether it has been marked as done */
    protected boolean isDone;

    /**
     * Class constructor with status to be false by default.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns status of the task.
     *
     * @return Status icon, tick for done tasks and X for undo tasks.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Overrides String representation of tasks.
     *
     * @return  String representation of status and description.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    /**
     * Returns description of the task.
     *
     * @return Description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Mark this task status as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }
}
