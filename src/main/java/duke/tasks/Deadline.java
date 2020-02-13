package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Sets a deadline together with the task on hand.
 */
public class Deadline extends DateTask {
    /** 24-hr time of deadline. */
    private LocalTime finishByTime;
    /** Date of deadline. */
    private LocalDate finishByDate;
    /** Format of LocalDate. */
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");

    /**
     * Creates a deadline task with the given description and time.
     * Time and date are split from finishBy.
     * If time is "-" (not given), only the date will be set.
     * If date is "-" (not given), today's date will be taken.
     * At least time or date must be given.
     *
     * @param description Description of task.
     * @param finishByTime Time of deadline.
     * @param finishByDate Date of deadline.
     */
    public Deadline(String description, String finishByTime, String finishByDate, String...tags) {
        super(description);
        this.finishByTime = LocalTime.parse(finishByTime);
        this.finishByDate = LocalDate.parse(finishByDate, dateFormatter);
        for (String tag : tags) {
            super.addTag(tag);
        }
    }

    @Override
    public String toString() {
        String date = finishByDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        String dateTime = finishByTime != null
                ? finishByTime + ", " + date
                : date;

        return "[D]" + super.toString() + " (by: " + dateTime + ")";
    }

    /**
     * Retrieves the date of deadline.
     *
     * @return Date of deadline.
     */
    @Override
    public LocalDate getDate() {
        return finishByDate;
    }

    /**
     * Converts task into format used in save file.
     *
     * @return Save format of task in a string for easier back-conversion
     */
    @Override
    public String toSaveFormat() {
        char d = super.getIsDone() ? '1' : '0';

        String time = finishByTime == null ? "-" : finishByTime.toString();
        return "D | " + d + " | " + super.getDescription() + " | " + time
                + " | " + finishByDate.format(dateFormatter) + " | " + super.stringifyTagsToSaveFormat();
    }
}
