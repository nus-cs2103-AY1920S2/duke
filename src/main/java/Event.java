import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime dateTime;

    public Event(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {

        return String.format("[E]%s (at: %s)", super.toString(),
                this.dateTime.format(DateTimeFormatter.ofPattern("" + "MMM d yyyy, h:mm a")));
    }

    public String toFileString() {
        return "E " + super.toFileString() + " | " + this.dateTime;
    }
}