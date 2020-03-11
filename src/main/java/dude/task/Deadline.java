package dude.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate dueDate;

    /**
     * Initializes a Deadline task with given details, due date and completion status indicated by isDone.
     *
     * @param details description of the deadline.
     * @param dueDate due date of the deadline.
     * @param isDone completion status of the deadline.
     */
    public Deadline(String details, LocalDate dueDate, boolean isDone) {
        super(details, isDone);
        this.dueDate = dueDate;
    }

    /**
     * Indicates if this Task occurs on the given date.
     *
     * @param date date of interest as to whether the Task occurs on that date.
     * @return true if date is exactly the dueDate of this Deadline, false otherwise.
     */
    @Override
    public boolean occursOn(LocalDate date) {
        return date.isEqual(this.dueDate);
    }

    /**
     * Returns a string representation of the Task, meant to be understood by users.
     *
     * @return String displaying Task type, isDone status, Task description and dueDate,
     *         in the format: [D][isDone] description (by: dueDate)
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                dueDate.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }

    /**
     * Returns a string representation of the Task, meant to be written to a plain text file and easily parsed.
     *
     * @return formatted string in the format: D|isDone|description|dueDate.
     */
    @Override
    public String storeFormat() {
        return String.format("D|%s|%s|%s", getStatusIcon(), getDetails(), this.dueDate);
    }
}