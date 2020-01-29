package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected static final String DATE_OUTPUT_PATTERN = "MMM dd, yyyy";
    protected LocalDate by;

    /**
     * Constructor of a Deadline.
     *
     * @param description Description of the Deadline.
     * @param by A string representing the date of the Deadline.
     * @throws DukeException If input date string is not of the ISO-LOCAL-DATE format (yyyy-mm-dd).
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);

        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("The input '/by' argument is invalid.\n" +
                    "\tExpected ISO-LOCAL-DATE format (e.g.: yyyy-mm-dd, or 2020-08-25)");
        }
    }

    /**
     * Overloaded constructor of a Deadline.
     * The LocalDate is passed in directly in this version of the constructor.
     *
     * @param description The description of the Deadline.
     * @param by The LocalDate of the Deadline.
     */
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
