import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime dateTime;

    public Event(String taskTitle, LocalDateTime dateTime) {
        super(taskTitle);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        String dateTimePattern = "MMM d yyyy HH:mm";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimePattern);
        return "[E]" + super.toString() + " (at: " + dateTimeFormatter.format(dateTime) + ")";
    }
}
