package seedu.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate deadlineTime;

    public Deadline(String description, LocalDate deadlineTime) {
        super(description);
        this.deadlineTime = deadlineTime;
    }

    @Override
    public String toString() {
        String formattedDeadlineTime = " (by: " + this.deadlineTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        return "[D]" + super.toString() + formattedDeadlineTime;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public LocalDate getTime() {
        return deadlineTime;
    }
}
