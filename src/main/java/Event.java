import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task {
    private LocalDate at;

    public Event(String content, String at) {
        super(content);
        this.at = LocalDate.parse(at);
    }

    @Override
    public String toStore() {
        return "[E]" + super.toStore() + " (at: " + this.at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ")";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at.format(DateTimeFormatter.ofPattern("MMM d yyy")) + ")";
    }
}
