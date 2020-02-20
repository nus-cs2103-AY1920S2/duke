package duke.task;

import duke.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * <h1>Deadline Class</h1>
 * A subclass of Task class. Record the description and due date of the deadline task.
 *
 * @author Eng Xuan En
 */
public class Deadline extends Task {
    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
    DateTimeFormatter storeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Class constructor of Deadline.
     *
     * @param description description of the deadline
     * @param due         date due for the deadline
     * @throws DukeException Occur when date is invalid.
     */
    public Deadline(String description, String due) throws DukeException {
        super(description);
        type = "deadline";

        try {
            period = LocalDate.parse(due);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please give valid date in deadline [description] /by [yyyy-mm-dd] format.");
        }
    }

    /**
     * Get period in "yyyy-mm-dd" format.
     *
     * @return date in "yyyy-mm-dd"
     */
    @Override
    public String getPeriod() {
        return period.format(storeFormatter);
    }

    /**
     * Get string in [D][Y or N] {description of the task} (by: {due date of the task}) format.
     *
     * @return String in certain format
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + period.format(outputFormatter) + ")";
    }
}
