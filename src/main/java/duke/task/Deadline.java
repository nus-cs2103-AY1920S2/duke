package duke.task;

import duke.Duke;
import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task that has a specific end date.
 */
public class Deadline extends Task {
    protected LocalDateTime dueDate;

    public Deadline(String identifier, String dueDate) throws DukeException {
        super(identifier);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy H:m");
        try {
            this.dueDate = LocalDateTime.parse(dueDate, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("\t" + "Please write the date and time in this format:" +
                        "dd-MM-yyyy h:m. For example, 05-27-1997 21:02 is the format" +
                        " to represent 9:02pm on 27 May 1997");
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
        return "Deadline: " + super.toString() + " (please complete by " + dueDate + ")";
    }
}
