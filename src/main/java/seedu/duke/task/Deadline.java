package seedu.duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline Object.
 */
public class Deadline extends Task {
    protected LocalDate deadlineDate;

    /**
     * Represents a Deadline Object.
     *
     * @param description The details of the deadline.
     * @param deadlineDate The date for the deadline.
     */
    public Deadline(String description, LocalDate deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    @Override
    public String toString() {
        String formattedDeadlineTime = " (by: "
                + deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        return "[D]" + super.toString() + formattedDeadlineTime;
    }

    public LocalDate getDate() {
        return deadlineDate;
    }

    @Override
    public int compareTo(Task task) {
        if (task instanceof Deadline) {
            int cmp = getDate().compareTo(((Deadline) task).getDate());
            if (cmp == 0) {
                return toString().compareTo(task.toString());
            } else {
                return cmp;
            }
        } else if (task instanceof Event) {
            int cmp = getDate().compareTo(((Event) task).getDate());
            if (cmp == 0) {
                return toString().compareTo(task.toString());
            } else {
                return cmp;
            }
        } else {
            return toString().compareTo(task.toString());
        }
    }
}
