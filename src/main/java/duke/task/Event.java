package duke.task;

import duke.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event.
 */
public class Event extends Task {
    protected LocalDate date;
    protected LocalTime startTime;
    protected LocalTime endTime;

    /**
     * Constructs an Event with the specified description and date and time.
     * @param description The description of the event.
     * @param at The date and time of the event in yyyy-mm-dd hh:mm-hh:mm format.
     * @throws DukeException If the date or time is in the incorrect format.
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            String[] dateTimeArr = at.split(" ");
            this.date = LocalDate.parse(dateTimeArr[0]);
            String[] timeArr = dateTimeArr[1].split("-");
            this.startTime = LocalTime.parse(timeArr[0]);
            this.endTime = LocalTime.parse(timeArr[1]);
            if (startTime.isAfter(endTime)) {
                throw new DukeException("Start time cannot be after end time.");
            }
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Incorrect date or time format. Format required: yyyy-mm-dd hh:mm-hh:mm");
        }
    }

    /**
     * Constructs an Event with the specified description, date and time and status.
     * @param description The description of the event.
     * @param at The date and time of the event in yyyy-mm-dd hh:mm-hh:mm format.
     * @param isDone Whether the event is done.
     */
    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        String[] dateTimeArr = at.split(" ");
        this.date = LocalDate.parse(dateTimeArr[0]);
        String[] timeArr = dateTimeArr[1].split("-");
        this.startTime = LocalTime.parse(timeArr[0]);
        this.endTime = LocalTime.parse(timeArr[1]);
    }

    /**
     * Returns the date of the event.
     * @return The date of the event.
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Returns a string representation of the event for saving to the disk.
     * @return String representation of the event for saving to the disk.
     */
    @Override
    public String formatToSave() {
        return "E" + super.formatToSave() + " | " + date + " " + startTime + "-" + endTime;
    }

    /**
     * Returns a string representation of the event for printing.
     * @return String representation of the event for printing.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " " + startTime + "-" + endTime + ")";
    }
}
