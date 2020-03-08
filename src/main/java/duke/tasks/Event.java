package duke.tasks;

import duke.exceptions.DukeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * An Event Task that takes in date and time.
 */
public class Event extends Task {
    protected LocalDate localDate;
    protected LocalTime localTime;

    String inputTimePattern = "HH:mm";
    DateTimeFormatter inputTimeFormatter = DateTimeFormatter.ofPattern(inputTimePattern);

    public Event(String description, String localDate, String localTime) throws DukeException {
        super(description);
        try {
            this.localDate = LocalDate.parse(localDate);
            this.localTime = LocalTime.parse(localTime, inputTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Parker.. Try: event {DESCRIPTION} /at YYYY-MM-DD HH:mm");
        }
    }
    
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                        + localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", "
                        + LocalTime.parse(localTime.toString(), inputTimeFormatter) + ")";
    }

    public String formatText() {
        return "E | " + (this.isDone ? "1" : "0") + " | " + description + " | "
                        + localDate + " | " + localTime + "\n";
    }
}

