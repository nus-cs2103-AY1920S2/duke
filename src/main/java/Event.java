import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private static String dateTimePattern = "MMM d yyyy HH:mm";
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimePattern);
    protected LocalDateTime dateTime;

    public Event(String taskTitle, LocalDateTime dateTime) {
        super(taskTitle);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateTimeFormatter.format(dateTime) + ")";
    }

    public String getDateTime() {
        return dateTimeFormatter.format(dateTime);
    }
}
