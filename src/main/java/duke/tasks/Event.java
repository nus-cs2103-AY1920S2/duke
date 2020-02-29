package duke.tasks;

import duke.Duke;
import duke.exceptions.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected String at;
    protected LocalDate ld;

    /**
     * constructs an Event object.
     * @param description name of the task
     * @param at date of the event given in YYYY-MM-DD format
     * @throws DukeException catches exception when date is entered in incorrect format
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            ld = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter date of event in YYYY-MM-DD format.");
        }
    }

    public String toSaveForm() {
        return "E , " + super.getStatusIcon() + " , " + description + " , " + ld.toString() + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + ld.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
