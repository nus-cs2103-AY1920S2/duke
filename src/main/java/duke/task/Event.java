package duke.task;

import duke.exceptions.InvalidDateTimeFormatException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event class that extends Task, by adding a new parameter called "at", to dictate when the event is happening
 */
public class Event extends Task {

    /**
     * Variable to store when the Event is happening at
     */
    public LocalDateTime at;

    /**
     * Constructor for Event object, specifying the description and datetime at which the event is happening
     * @param description Description of the Event
     * @param at String representation of Date & Time at which the event is happening
     */
    public Event(String description, String at) throws InvalidDateTimeFormatException {
        super(description);
        try {
            DateTimeFormatter inputDtf = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime outputDt = LocalDateTime.parse(at, inputDtf);
            DateTimeFormatter outputDtf = DateTimeFormatter.ofPattern("d MMMM yyyy, h:mm a");
            this.at = LocalDateTime.parse(outputDt.format(outputDtf), DateTimeFormatter.ofPattern("d MMMM yyyy, h:mm a"));
        } catch (DateTimeException exception) {
            throw new InvalidDateTimeFormatException();
        }
    }

    /**
     * Returns a String representation of the Task object
     * @return a String representation of the Task object
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("d MMMM yyyy, h:mm a"))
            + ")";
    }

    public boolean snooze(LocalDateTime datetime) {
        this.at = datetime;
        return true;
    }
}