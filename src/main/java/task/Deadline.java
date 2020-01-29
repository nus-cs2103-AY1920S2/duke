package task;

import exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected static final String DATE_OUTPUT_PATTERN = "MMM dd, yyyy";
    protected LocalDate by;

    public Deadline(String description, String by) throws DukeException {
        super(description);

        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("The input '/by' argument is invalid.\n" +
                    "\tExpected ISO-LOCAL-DATE format (e.g.: yyyy-mm-dd, or 2020-08-25)");
        }
    }

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    protected String getTypeIcon() {
        return "[D]";
    }

    @Override
    protected TaskType getTaskType() {
        return TaskType.TASK_TYPE_DEADLINE;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern(DATE_OUTPUT_PATTERN)) + ")";
    }
}
