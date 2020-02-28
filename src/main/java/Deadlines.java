import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 * Class for Deadlines.
 */
public class Deadlines extends Task {
    protected String by;
    private LocalDate date;

    /**
     * Deadline constructor.
     * @param description description of deadlines
     * @param isDone if task is done
     * @param by deadline date in format yyyy-mm-dd
     */
    public Deadlines(String description, Boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
        date = LocalDate.parse(by);
    }

    /**
     * returns date of deadline.
     * @return date task is due
     */
    public String getDeadline() {
        return this.by;
    }

    /**
     * changes format of date.
     * @return String value after modifying the date from yyyy-mm-dd to MMM DD YYYY
     */
    @Override
    public String toString() {
        String simpleMonth = date.getMonth().toString().substring(0,3);
        String formattedDate = simpleMonth + " " + date.getDayOfMonth() + " " + date.getYear();
        return "[D]" + super.toString() + " (By: " + formattedDate + ")";
    }
}
