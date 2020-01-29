import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event
 *
 * CS2103T AY19/20 Semester 2
 * Individual project
 * Duke project
 *
 * 29 Jan 2020
 *
 * @author Jel
 */
public class Event extends Task {
    private LocalDate at;

    Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    protected LocalDate getScheduledTime() {
        return this.at;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)\n", super.toString(), this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
