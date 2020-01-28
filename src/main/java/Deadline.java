import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Represents a deadline task. A <code>Deadline/code> object
 * corresponds to a command represented by the command and a description e.g.,
 * <code>"read", "2019-05-10 1800"</code>
 */
public class Deadline extends Task {

    String by;
    LocalDate date;
    LocalTime time;

    /**
     * A constructor for a Deadline object.
     * @param description description of task
     * @param by date and time of task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;

        String[] arr = by.split(" ");
        this.date = LocalDate.parse(arr[0]);

        if(arr.length > 1) {
            char[] charArr = arr[1].toCharArray();
            String hour = "" + charArr[0] + charArr[1];
            String mins = "" + charArr[2] + charArr[3];
            this.time = LocalTime.parse(hour + ":" + mins);
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + time.format(DateTimeFormatter.ofPattern("hhmma")) + ")";
    }
}
