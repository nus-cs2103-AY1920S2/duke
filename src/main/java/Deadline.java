import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * The Deadline class represents a task that need to be
 * done before a specific date/time e.g., submit report by 11/10/2019 5pm
 */
public class Deadline extends Task {
    protected LocalDate date;
    protected LocalTime time;

    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +
                " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                " " + time.format(DateTimeFormatter.ofPattern("HHmm")) +
                ")";
    }
}
