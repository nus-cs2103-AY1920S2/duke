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

    public LocalDate getDateBy() {
        return deadlineDate;
    }
}
