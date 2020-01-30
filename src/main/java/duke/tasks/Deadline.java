package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.enums.ErrorCodes;
import duke.exceptions.DukeException;

public class Deadline extends Task {
    private String deadlineDate;
    private LocalDate parsedDate;

    /**
     * Deadline constructor.
     *
     * @param deadlineArgs Joined string of the user input command arguements
     * @throws DukeException Incorrect format/missing date
     */
    public Deadline(String deadlineArgs) throws DukeException {
        super(deadlineArgs.split(" /by ")[0]);
        try {
            this.deadlineDate = deadlineArgs.split(" /by ")[1];
            this.parsedDate = LocalDate.parse(deadlineDate);
        } catch (DateTimeParseException e) {
            throw new DukeException(ErrorCodes.INVALID_DATE_FORMAT);
        } catch (Exception e) {
            throw new DukeException(ErrorCodes.MISSING_DEADLINE_DATE);
        }
    }

    /**
     * A method to return the details of the current Task object.
     * Contains the task done status and the task name.
     *
     * @return A String representation of the task details.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
            + this.parsedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toFileString() {
        return "D" + super.toFileString() + " | " + deadlineDate;
    }
}