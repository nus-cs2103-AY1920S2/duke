import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDate atDate;
    private LocalTime atTime;

    public Event(String description, LocalDate atDate, LocalTime atTime) {
        super(description.strip());
        this.atDate = atDate;
        this.atTime = atTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s %s)", super.toString(), atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")), atTime);
    }
}