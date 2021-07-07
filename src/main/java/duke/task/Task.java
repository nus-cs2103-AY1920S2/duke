package duke.task;

/**
 * Class to represent a Task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns type of task.
     *
     * @return type of task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status icon, i.e. a tick or a cross
     *
     * @return the status icon
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Mark a task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns the initial letter of the type of task, eg D for Deadline.
     *
     * @return the task icon
     */
    public String getTaskIcon() {
        return "0";
    }

    /**
     * Returns true if event is associated with time, false otherwise.
     *
     * @return whether event associated with time
     */
    public boolean hasTime() {
        return false;
    }

}