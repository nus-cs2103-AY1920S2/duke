import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task {

    protected LocalDate by;
    public String type;

    public Event(String description, LocalDate by) {
        super(description);
        this.by = by;
        this.type = "event";
    }

    @Override
    public LocalDate getBy() {
        return by;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String toString() {

        if (super.getStatus() == 0) {
            return "[E][✗]" + super.toString() + " (at: " + by + ")";
        } else {
            return "[E][✓]" + super.toString() + " (at: " + by + ")";
        }
    }
}