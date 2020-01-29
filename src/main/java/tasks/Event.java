package tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Sets a start time together with the task on hand.
 */
public class Event extends DateTask {
    /** 24-hr time of event. */
    private LocalTime startAtTime;
    /** Date of event. */
    private LocalDate startAtDate;

    /**
     * Creates an event task with the given description and time.
     * Time and date are split from startAt.
     * If time is "-" (not given), only the date will be set.
     * If date is "-" (not given), today's date will be taken.
     * At least time or date must be given.
     *
     * @param description Description of task.
     * @param startAt Time and date of event.
     * @throws DateTimeException When there are insufficient parameters for startAt.
     */
    public Event(String description, String startAt) throws DateTimeException {
        super(description);

        String[] fields = startAt.split(" ");
        if (fields.length != 2) {
            throw new DateTimeException("Insufficient parameters for date/time");
        }

        this.startAtTime = fields[0].equals("-")
                ? null
                : LocalTime.parse(fields[0]);

        this.startAtDate = fields[1].equals("-")
                ? LocalDate.now()
                : LocalDate.parse(fields[1]);
    }

    @Override
    public String toString() {
        String date = this.startAtDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String dateTime = this.startAtTime != null
                ? this.startAtTime + ", " + date
                : date;

        return "[E]" + super.toString() + " (at: " + dateTime + ")";
    }

    /**
     * Retrieves the date of event.
     *
     * @return Date of event.
     */
    @Override
    public LocalDate getDate() {
        return this.startAtDate;
    }

    /**
     * Converts task into format used in save file.
     *
     * @return Save format of task in a string for easier back-conversion.
     */
    @Override
    public String toSaveFormat() {
        char d = super.getIsDone() ? '1' : '0';

        String time = this.startAtTime == null ? "-" : this.startAtTime.toString();

        return "E | " + d + " | " + super.getDescription() + " | " + time
                + " " + this.startAtDate;
    }
}
