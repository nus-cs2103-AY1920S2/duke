import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate on;

    public Event(String description, LocalDate on) {
        super(description);
        this.on = on;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (on: " + on.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}