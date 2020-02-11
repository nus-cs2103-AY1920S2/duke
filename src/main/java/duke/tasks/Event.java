package duke.tasks;

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
    /** Format of LocalDate. */
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");

    /**
     * Creates an event task with the given description and time.
     * Time and date are split from startAt.
     * If time is "-" (not given), only the date will be set.
     * If date is "-" (not given), today's date will be taken.
     * At least time or date must be given.
     *
     * @param description Description of task.
     * @param startAtTime Time of event.
     * @param startAtDate Date of event.
     */
    public Event(String description, String startAtTime, String startAtDate, String...tags) {
        super(description);
        this.startAtTime = LocalTime.parse(startAtTime);
        this.startAtDate = LocalDate.parse(startAtDate, dateFormatter);
        for (String tag : tags) {
            super.addTag(tag);
        }
    }

    @Override
    public String toString() {
        String date = startAtDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        String dateTime = startAtTime != null
                ? startAtTime + ", " + date
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
        return startAtDate;
    }

    /**
     * Converts task into format used in save file.
     *
     * @return Save format of task in a string for easier back-conversion.
     */
    @Override
    public String toSaveFormat() {
        char d = super.getIsDone() ? '1' : '0';

        String time = startAtTime == null ? "-" : startAtTime.toString();

        return "E | " + d + " | " + super.getDescription() + " | " + time
                + " | " + startAtDate.format(dateFormatter) + " | " + super.stringifyTagsToSaveFormat();
    }
}
