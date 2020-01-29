package seedu.duke;

import java.time.LocalDate;

/**
 * Superclass of Deadline, Event, and Todo class.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task based on the description input by the user.
     *
     * @param description the details of the task created
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Prints the done status of a task.
     *
     * @return "Y" if the task is marked done, "N" otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "Y" : "N"); //return tick or X symbols
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Checks if a task is marked as done.
     *
     * @return true if a task is marked as done
     */
    public boolean getDoneStatus() {
        return isDone;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    public abstract String getType();

    public abstract LocalDate getTime();
}
