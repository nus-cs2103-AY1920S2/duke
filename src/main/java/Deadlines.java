import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 * Class for Deadlines obj
 */
public class Deadlines extends Task{
    protected String by;
    private LocalDate date;

    public Deadlines(String description, Boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
        date = LocalDate.parse(by);
    }

    public String getDeadline() {
        return this.by;
    }

    /**
     * @return String value after modifying the date from yyyy-mm-dd to MMM DD YYYY
     */
    @Override
    public String toString() {
        String simpleMonth = date.getMonth().toString().substring(0,3);
        String formattedDate = simpleMonth + " " + date.getDayOfMonth() + " " + date.getYear();
        return "[D]" + super.toString() + " (By: " + formattedDate + ")";
    }
}
