import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Event extends Task {
    private LocalDateTime at;

    public Event(String description, String at) {
        super(description);
        this.at = LocalDateTime.parse(at, inFormatter);
    }

    public String toString() {
        return "[E]" + super.toString() + "(at: " + at.format(outFormatter) + ")";
    }
}