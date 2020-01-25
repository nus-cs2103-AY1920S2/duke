import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents a task that start at a specific time
 * and ends at a specific time e.g., team project meeting on 2/10/2019 2-4pm
 */
public class Event extends Task {
    protected String fromTimeToTime;
    protected LocalDate date;

    public Event(String description, LocalDate date, String fromTimeToTime) {
        super(description);
        this.date = date;
        this.fromTimeToTime = fromTimeToTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                " " + fromTimeToTime +
                ")";
    }
}
