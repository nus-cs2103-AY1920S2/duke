import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime event;
    public Event(String task, LocalDateTime event) {
        super(task);
        this.event = event;
    }

    public String toString() {
        return String.format("[E]%s(at: %s)", super.toString(), this.event.format(DateTimeFormatter.ofPattern("" +
                "MMM d yyyy, h:mm a")));
    }
}
