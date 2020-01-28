/**
 * Event task that specifies a upcoming task that on a certain date and time
 */
package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected static String dateTimePattern = "MMM d yyyy HH:mm";
    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Event.dateTimePattern);
    protected LocalDateTime dateTime;

    /**
     * Checks If user input date and time is of the correct format
     * @param inputDateTime Date and time input from user
     * @return LocalDateTime object of format "yyyy-MM-dd HHmm" if input is of correct format
     * @throws DateTimeParseException If input format is wrong
     */
    public static LocalDateTime parseDateTime(String inputDateTime) throws DateTimeParseException {
        String inputDateTimePattern = "yyyy-MM-dd HHmm";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(inputDateTimePattern);
        LocalDateTime localDateTime = LocalDateTime.parse(inputDateTime, dateTimeFormatter);
        return localDateTime;
    }

    /**
     * Creates an Event task
     * @param taskTitle Title of upcoming task
     * @param dateTime Date and time of upcoming task
     */
    public Event(String taskTitle, LocalDateTime dateTime) {
        super(taskTitle);
        this.dateTime = dateTime;
    }

    /**
     * Returns a string representation an Event task
     * @return A string representation an Event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateTimeFormatter.format(dateTime) + ")";
    }

    /**
     * Returns a string representation of the date and time of this task
     * @return A string representation of the date and time of this task
     */
    public String getDateTime() {
        return dateTimeFormatter.format(dateTime);
    }
}
