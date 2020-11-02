package duke.task;

import duke.exception.DukeException;
import duke.exception.InvalidEventException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that occurs on a certain date and time.
 */
public class Event extends Task {
    protected LocalDateTime timePeriod;

    /**
     * Constructs Event objects.
     * @param identifier Name of Event.
     * @param timePeriod Time and Date of Event.
     * @throws DukeException Thrown when wrong format is used.
     */
    public Event(String identifier, String timePeriod) throws DukeException {
        super(identifier);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy H:m");
        try {
            this.timePeriod = LocalDateTime.parse(timePeriod, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidEventException();
        }
    }

    /**
     * Gets Date and Time of event.
     * @return A LocalDateTime object of an event.
     */
    public LocalDateTime getTimePeriod() {
        return this.timePeriod;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy H:m");
        return "Event: " + super.toString() + " (at: " + formatter.format(timePeriod) + ")";
    }
}
