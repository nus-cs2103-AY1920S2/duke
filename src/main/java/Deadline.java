import java.util.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Represents a deadline in the ChatBot task list.
 * Contains date and time of due deadline and description of the task.
 */
public class Deadline extends Task {

    public String dateTime;
    public LocalDateTime date;

    /**
     * Constructor for the Deadline task.
     * @param taskName description of the deadline.
     * @param dateTime date and time when the task is due.
     */
    public Deadline(String taskName, String dateTime) {
        super(taskName, "D");
        this.dateTime = dateTime;
        this.constructDate(dateTime);
    }

    /**
     * Contructor for the Deadline task.
     * @param taskName description of the deadline.
     * @param dateTime date and time when the task is due.
     * @param priority priority of the deadline.
     */
    public Deadline(String taskName, String dateTime, int priority) {
        super(taskName, "D", priority);
        this.dateTime = dateTime;
        this.constructDate(dateTime);
    }

    /**
     * Helper function to create the LocalDateTime object from the given String.
     * @param dateTime the String that represents the date and time in the format dd-mm-yyy HH:mm.
     */
    public void constructDate(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.date = LocalDateTime.parse(this.dateTime, formatter);
    }

    @Override
    /**
     * The String representation of the deadline.
     * @return String representation of the task (type, whether is it done or not, description
     * and lastly date of due date.
     */
    public String toString() {
        String message = "";
        String dateToBePrinted = this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        if (this.getDone()) {
            message += "[" + this.getTaskType() + "]" + "[" + "✓" + "] "
                    + this.getTaskName() + " (by: " + dateToBePrinted + ")";
        } else {
            message +=  "[" + this.getTaskType() + "]" + "[" + "✗" + "] "
                    + this.getTaskName() + " (by: " + dateToBePrinted + ")";
        }
        message += " Priority: " + this.getPriorityString();
        return message;
    }

    /**
     * Function to test whether the String date is in the correct format.
     * @param date String representation of the due date.
     * @throws Exception when the date is not the right format.
     */
    public static void validDate(String date) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime d = LocalDateTime.parse(date, formatter);
    }

    public String getDateTime() {
        return this.dateTime;
    }
}
