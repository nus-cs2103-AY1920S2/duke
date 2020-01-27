package dude.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate dueDate;

    /**
     *
     * @param details Description of the deadline.
     * @param dueDate due date of the deadline.
     * @param isDone completion status of the deadline.
     */
    public Deadline(String details, LocalDate dueDate, boolean isDone) {
        super(details, isDone);
        this.dueDate = dueDate;
    }

    /**
     *
     * @param date Date of interest as to whether the Task occurs on that date.
     * @return true if date is exactly the dueDate of this Deadline, false otherwise.
     */
    @Override
    public boolean occursOn(LocalDate date) {
        return date.isEqual(this.dueDate);
    }

    /**
     *
     * @return String displaying Task type, isDone status, Task description and dueDate,
     *  in the format: [D][isDone] description (by: dueDate)
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                dueDate.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }

    /**
     *
     * @return formatted string in the format: D|isDone|description|dueDate.
     */
    @Override
    public String storeFormat() {
        return String.format("D|%s|%s|%s", getStatusIcon(), getDetails(), this.dueDate);
    }
}