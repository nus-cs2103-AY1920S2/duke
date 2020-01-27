import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate by;

    public Event(String desc, LocalDate by) {
        super(desc);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + by.format(DateTimeFormatter.ofPattern("MMM d yyy")) + ")";
    }

}
