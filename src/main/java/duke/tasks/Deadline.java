/**
 * Deadline task that specifies a task that has to be done by a certain date
 */
package duke.tasks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class Deadline extends Task {

    public static String datePattern = "MMM d yyyy";
    //public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Deadline.datePattern);
    //protected Date date;
    public static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(Deadline.datePattern);
    protected LocalDate date;

    public static LocalDate parseDate(String inputDate) throws DateTimeParseException {
        String pattern = "yyyy-MM-dd";
        DateTimeFormatter inputDateFormat = DateTimeFormatter.ofPattern(pattern);
        LocalDate date = LocalDate.parse(inputDate, inputDateFormat);
        return date;
    }

    /**
     * Creates a Deadline task
     * @param taskTitle Title of task to be completed
     * @param date Deadline of task
     */
    public Deadline(String taskTitle, LocalDate date) {
        super(taskTitle);
        this.date = date;
    }

    /**
     * Returns a string representation of a Deadline task
     * @return A string representation of a Deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateFormatter.format(date) + ")";
    }

    /**
     * Returns a string representation of this task's deadline
     * @return A string representation of this task's deadline
     */
    public String getDeadline() {
        return dateFormatter.format(date);
    }
}