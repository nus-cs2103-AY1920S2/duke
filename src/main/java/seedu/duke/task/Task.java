package seedu.duke.task;

/**
 * Represents a Task object.
 */
public abstract class Task implements Comparable<Task> {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task based on the description input by the user.
     *
     * @param description The details of the task created.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Prints the done status of a task.
     *
     * @return "Y" if the task is marked done, "N" otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "Y" : "N"); //return tick or X symbols
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Checks if a task is marked as done.
     *
     * @return true if a task is marked as done.
     */
    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public abstract int compareTo(Task task);
}
