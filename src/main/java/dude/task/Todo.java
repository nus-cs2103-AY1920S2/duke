package dude.task;

import java.time.LocalDate;

public class Todo extends Task {
    /**
     * Initializes a Todo task with given details and completion status indicated by isDone.
     *
     * @param details description of the task.
     * @param isDone completion status of the task.
     */
    public Todo(String details, boolean isDone) {
        super(details, isDone);
    }

    /**
     * Indicates if this Task occurs on the given date.
     *
     * @param date date of interest as to whether the Task occurs on that date.
     * @return false since Todos do not have a specified date.
     */
    @Override
    public boolean occursOn(LocalDate date) {
        return false;
    }

    /**
     * Returns a string representation of the Task, meant to be understood by users.
     *
     * @return String displaying Task type, isDone status and Task description,
     *         in the format: [T][isDone] description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the Task, meant to be written to a plain text file and easily parsed.
     *
     * @return formatted string in the format: T|isDone|description.
     */
    @Override
    public String storeFormat() {
        return String.format("T|%s|%s", getStatusIcon(), getDetails());
    }
}