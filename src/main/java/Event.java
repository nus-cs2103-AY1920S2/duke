import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Event extends Task {

    protected String at;
    protected LocalDate date;
    protected LocalTime time;
    protected LocalDateTime dateTime;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, LocalDate date) {
        super(description);
//        this.dateTime = dateTime;
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
