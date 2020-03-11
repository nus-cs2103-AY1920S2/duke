package dude.task;

import java.time.LocalDate;

public abstract class Task {
    private final String details;
    private boolean isDone;

    /**
     * Initializes a Task object with given details and completion status indicated by isDone.
     *
     * @param details description of the task.
     * @param isDone completion status of the task.
     */
    public Task(String details, boolean isDone) {
        this.details = details;
        this.isDone = isDone;
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the task details supplied by user.
     *
     * @return description of the task.
     */
    public String getDetails() {
        return details;
    }

    /**
     * Indicates whether the task is completed.
     *
     * @return true if Task is complete, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Indicates if this Task occurs on the given date.
     *
     * @param date date of interest as to whether the Task occurs on that date.
     * @return true if this Task occurs on the given date, false otherwise.
     */
    public abstract boolean occursOn(LocalDate date);

    /**
     * Returns a string representation of the Task, meant to be understood by users.
     *
     * @return String displaying isDone status and Task description in the format: [isDone] description
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), details);
    }

    /**
     * Returns a string representation of the Task, meant to be written to a plain text file and easily parsed.
     *
     * @return formatted string with pipe character '|' between each field.
     */
    public abstract String storeFormat();

    /**
     * Returns a string representation of the completion status of the task, meant to be understood by users.
     *
     * @return "O" if this Task is complete, "X" otherwise.
     */
    protected String getStatusIcon() {
        return (isDone ? "O" : "X"); 
    }
}