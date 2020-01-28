import java.time.LocalDate;

public class Event extends Task {
    protected LocalDate by;

    public Event(String description, String by) {
        super(description);
        this.by = convertToLocalDate(by);
    }

    @Override
    public String toString() {
        String newBy = String.format("%d %s %d", this.by.getDayOfMonth(), this.by.getMonth(), this.by.getYear());
        return "[D]" + super.toString() + " (at: " + newBy + ")";
    }
}
