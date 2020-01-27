import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate duration;

    public Event(String desc, LocalDate duration) {
        super(desc);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return String.format("[E] [%s] %s (at: %s)", this.getStatus(), this.description,
                this.duration.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
