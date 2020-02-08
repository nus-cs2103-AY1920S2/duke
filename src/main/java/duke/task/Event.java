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
     *
     * @param description The description of the event.
     * @param at The date and time of the event in yyyy-mm-dd hh:mm-hh:mm format.
     * @throws DukeException If the date or time is in the incorrect format.
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            String[] dateTimeArray = at.split(" ");
            this.date = LocalDate.parse(dateTimeArray[0]);
            String[] timeArray = dateTimeArray[1].split("-");
            this.startTime = LocalTime.parse(timeArray[0]);
            this.endTime = LocalTime.parse(timeArray[1]);
            if (startTime.isAfter(endTime)) {
                throw new DukeException("Start time cannot be after end time.");
            }
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Incorrect date or time format. Format required: yyyy-mm-dd hh:mm-hh:mm");
        }
    }

    /**
     * Constructs an Event with the specified description, date and time and status.
     *
     * @param description The description of the event.
     * @param at The date and time of the event in yyyy-mm-dd hh:mm-hh:mm format.
     * @param isDone Whether the event is done.
     */
    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        String[] dateTimeArray = at.split(" ");
        this.date = LocalDate.parse(dateTimeArray[0]);
        String[] timeArray = dateTimeArray[1].split("-");
        this.startTime = LocalTime.parse(timeArray[0]);
        this.endTime = LocalTime.parse(timeArray[1]);
    }

    /**
     * Returns the date of the event.
     *
     * @return The date of the event.
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Sets the date of the event to the specified date.
     * @param date The date of the event.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Sets the start time of the event to the specified time.
     * @param time The start time of the event.
     */
    public void setStartTime(LocalTime time) {
        this.startTime = time;
    }

    /**
     * Sets the end time of the event to the specified time.
     * @param time The end time of the event.
     */
    public void setEndTime(LocalTime time) {
        this.endTime = time;
    }

    /**
     * Returns a string representation of the event for saving to the disk.
     *
     * @return String representation of the event for saving to the disk.
     */
    @Override
    public String formatToSave() {
        return "E" + super.formatToSave() + " | " + date + " " + startTime + "-" + endTime;
    }

    /**
     * Returns a string representation of the event for printing.
     *
     * @return String representation of the event for printing.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " " + startTime + "-" + endTime + ")";
    }
}
