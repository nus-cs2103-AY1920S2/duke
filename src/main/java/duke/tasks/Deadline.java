/**
 * Deadline task that specifies a task that has to be done by a certain date
 */
package duke.tasks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    public static String datePattern = "MMM d yyyy";
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Deadline.datePattern);
    protected Date date;

    public static Date parseDate(String inputDate) throws ParseException {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat inputDateFormat = new SimpleDateFormat(pattern);
        return inputDateFormat.parse(inputDate);
    }

    /**
     * Creates a Deadline task
     * @param taskTitle Title of task to be completed
     * @param date Deadline of task
     */
    public Deadline(String taskTitle, Date date) {
        super(taskTitle);
        this.date = date;
    }

    /**
     * Returns a string representation of a Deadline task
     * @return A string representation of a Deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + simpleDateFormat.format(date) + ")";
    }

    /**
     * Returns a string representation of this task's deadline
     * @return A string representation of this task's deadline
     */
    public String getDeadline() {
        return simpleDateFormat.format(date);
    }
}