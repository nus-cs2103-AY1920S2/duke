import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event object characterized by an instrution and a date and time.
 */
public class Event extends Task {
    private LocalDateTime time;

    /**
     * Creates a new Event with the given instruction and time.
     */
    public Event (String instruction, LocalDateTime time) {
        super(instruction);
        this.time = time;
    }

    /**
     * Gets the time of an event.
     * @return the time of this event.
     */
    public LocalDateTime getTime() {
        return this.time;
    }

    /**
     * Gets a string representation of an event.
     * @return the string representation of this event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: "
                + time.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
