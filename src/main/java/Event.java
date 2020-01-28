import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime at;

    public Event(int done, String taskName, LocalDateTime at) {
        super("event",done, taskName);
        this.at = at;
    }

    LocalDateTime getAt() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String formatDateTime = at.format(format);
        return at;
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm");
        String formatDateTime = at.format(format);
        return "[E]" + super.toString() + " (at: " + formatDateTime + ")";
    }
}