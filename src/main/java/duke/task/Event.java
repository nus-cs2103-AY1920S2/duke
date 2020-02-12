package duke.task;

import duke.exception.DukeException;
import duke.exception.DukeInvalidDateTimeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event.
 */
public class Event extends Task {

    protected LocalDate atDate;
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
            String[] dateAndTime = at.split(" ");
            this.atDate = LocalDate.parse(dateAndTime[0]);
            //time is optional
            if (dateAndTime.length == 2) {
                this.atTime = LocalTime.parse(dateAndTime[1]);
            } else {
                this.atTime = null;
            }
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateTimeException();
        }
    }

    /**
     * Returns a string representation of the event that will be saved in storage.
     *
     * @return The string representation of the event that will be saved in storage.
     */
    @Override
    public String toSaveName() {
        String time = (atTime != null ? " " + atTime : "");
        return "E" + super.toSaveName() + " | " + this.atDate + time + "\n";
    }

    /**
     * Returns a string representation of the event.
     *
     * @return The string representation of the event.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[MMM d yyyy][h:mma]");
        String time = (atTime != null ? " " + atTime.format(formatter) : "");
        return "[E]" + super.toString() + " (at: " + atDate.format(formatter) + time + ")";
    }
}
