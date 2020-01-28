package duke.task;

import duke.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * <h1>Deadline Class</h1>
 * A subclass of Task class. Record the description and due date of the deadline task.
 *
 * @author  Eng Xuan En
 */
public class Deadline extends Task {
    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
    DateTimeFormatter storeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Class constructor for Deadline.
     * @param description description of Deadline
     */
    public Deadline(String description, String due) throws DukeException {
        super(description);
        try {
            period = LocalDate.parse(due);
            type = "deadline";
        } catch(DateTimeParseException e) {
            throw new DukeException("Please give valid date in deadline [description] /by [yyyy-mm-dd] format.");
        }
    }

    /**
     * Get period in "yyyy-mm-dd" format.
     * @return date in "yyyy-mm-dd"
     */
    @Override
    public String getPeriod() {
        return period.format(storeFormatter);
    }

    /**
     * Get string in [D][tick or cross] {description of the task} (by: {due date of the task}) format.
     * @return String in certain format
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + period.format(outputFormatter) + ")";
    }
}
