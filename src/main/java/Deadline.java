import java.util.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Represents a deadline in the ChatBot task list.
 * Contains date and time of due deadline and description of the task.
 */
public class Deadline extends Task {

    public String DateTime;
    public LocalDateTime date;

    /**
     * Constructor for the Deadline task.
     * @param taskname description of the deadline.
     * @param DateTime date and time when the task is due.
     */
    public Deadline(String taskname, String DateTime) {
        super(taskname, "D");
        this.DateTime = DateTime;
        this.constructDate(DateTime);
    }

    /**
     * Helper function to create the LocalDateTime object from the given String.
     * @param dateTime the String that represents the date and time in the format dd-mm-yyy HH:mm.
     */
    public void constructDate(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm");
        this.date = LocalDateTime.parse(this.DateTime, formatter);
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
        if (this.done) {
            message += "[" + this.Tasktype +"]" + "[" + "\u2713" + "] " + this.taskname + " (by: " + dateToBePrinted + ")";
        } else {
            message +=  "[" + this.Tasktype +"]" + "[" + "\u2718" + "] " + this.taskname + " (by: " + dateToBePrinted + ")";
        }
        return message;
    }

    /**
     * Function to test whether the String date is in the correct format.
     * @param date String representation of the due date.
     * @throws Exception when the date is not the right format.
     */
    public static void validDate(String date) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm");
        LocalDateTime d = LocalDateTime.parse(date, formatter);
    }
}
