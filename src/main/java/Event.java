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

    public LocalDate getAt() {
        return this.at;
    }

    @Override
    public String toStorageString() {
        return String.format("E | %s | %s | %s\n", super.getStatusInteger(), super.getDesc(),
                this.getAt().toString());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + formatDate() + ")";
    }
}
