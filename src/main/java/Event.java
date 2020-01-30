import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;

    public Event(String desc, String at) {
        super(desc);
        // at variable must be in YYYY-mm-dd
        this.at = LocalDate.parse(at);
    }

    private String formatDate() {
        return at.format(DateTimeFormatter.ofPattern("dd MMM yy"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + formatDate() + ")";
    }
}
