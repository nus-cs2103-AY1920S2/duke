package dude.task;

import java.time.LocalDate;

public class Todo extends Task {
    /**
     *
     * @param details description of the task.
     * @param isDone completion status of the task.
     */
    public Todo(String details, boolean isDone) {
        super(details, isDone);
    }

    /**
     *
     * @param date date of interest as to whether the Task occurs on that date.
     * @return false since Todos do not have a specified date.
     */
    @Override
    public boolean occursOn(LocalDate date) {
        return false;
    }

    /**
     *
     * @return String displaying Task type, isDone status and Task description,
     *  in the format: [T][isDone] description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     *
     * @return formatted string in the format: T|isDone|description.
     */
    @Override
    public String storeFormat() {
        return String.format("T|%s|%s", getStatusIcon(), getDetails());
    }
}