package duke.task;

import duke.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
    DateTimeFormatter storeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Constructor for duke.task.Deadline
     * @param description description of duke.task.Deadline
     */
    public Deadline(String description, String due) throws DukeException{
        super(description);
        try {
            period = LocalDate.parse(due);
            type = "deadline";
        } catch(DateTimeParseException e) {
            throw new DukeException("Please give valid date in deadline [description] /by [yyyy-mm-dd] format.");
        }
    }

    /**
     * Get period
     * @return date in "yyyy-mm-dd"
     */
    @Override
    public String getPeriod() {
        return period.format(storeFormatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + period.format(outputFormatter) + ")";
    }
}
