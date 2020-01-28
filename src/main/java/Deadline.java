import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;
    protected LocalDate date;
    protected LocalTime time;
    protected LocalDateTime dateTime;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String format() {
        return "D" + " | " + (this.isDone ? "1" : "0") + " | " + description + " | " + date;
    }
    public Deadline(String description, LocalDate date) {
        super(description);
//        this.dateTime = dateTime;
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}