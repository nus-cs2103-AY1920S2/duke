package duke.tasks;

import duke.exceptions.DukeException;
import duke.util.Ui;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String by;
    protected LocalDate ld;

    /**
     * constructs a Deadline object.
     * @param description name of the task
     * @param by date for task in YYYY-MM-DD format
     * @throws DukeException catches exception when date is entered in incorrect format
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            ld = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter date of deadline in YYYY-MM-DD format.");
        }
    }

    public String toSaveForm() {
        return "D , " + super.getStatusIcon() + " , " + description + " , " + ld.toString() + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + ld.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
