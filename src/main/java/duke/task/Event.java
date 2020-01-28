package duke.task;

import duke.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event.
 */
public class Event extends Task {

    protected LocalDate at;
    protected LocalTime atTime;

    /**
     * Constructs an Event with the specified description and date and/or time.
     *
     * @param description The specified description of the event.
     * @param at The specified date and/or time of the event.
     * @throws DukeException If the date or time are wrongly formatted.
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            String[] temp = at.split(" ");
            this.at = LocalDate.parse(temp[0]);
            if (temp.length == 2) {
                this.atTime = LocalTime.parse(temp[1]);
            } else {
                this.atTime = null;
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!!! Format is yyyy-mm-dd HH:mm. Time is optional.");
        }
    }

    /**
     * Returns a string representation of the event that will be saved in storage.
     *
     * @return The string representation of the event that will be saved in storage.
     */
    @Override
    public String toSaveName() {
        return "E" + super.toSaveName() + " | " + this.at
                + (atTime != null ? " " + atTime : "") + "\n";
    }

    /**
     * Returns a string representation of the event.
     *
     * @return The string representation of the event.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[MMM d yyyy][h:mma]");
        return "[E]" + super.toString() + " (at: " +  at.format(formatter) +
                (atTime != null ? " " + atTime.format(formatter) : "") + ")";
    }
}
