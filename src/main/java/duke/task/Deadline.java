package duke.task;

import duke.exception.DukeException;
import duke.exception.InvalidDeadlineException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task that has a specific end date.
 */
public class Deadline extends Task {
    protected LocalDateTime dueDate;

    /**
     * Constructs Deadline objects.
     * @param identifier Name of Deadline.
     * @param dueDate Time and Date of Deadline.
     * @throws DukeException Thrown when wrong format is used.
     */
    public Deadline(String identifier, String dueDate) throws DukeException {
        super(identifier);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy H:m");
        try {
            this.dueDate = LocalDateTime.parse(dueDate, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDeadlineException();
        }
    }

    /**
     * Gets the due date of a deadline.
     * @return LocalDateTime object of the deadline's due date.
     */
    public LocalDateTime getDueDate() {
        return this.dueDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy H:m");
        return "Deadline: " + super.toString() + " (please complete by "
                + formatter.format(dueDate) + ")";
    }
}
