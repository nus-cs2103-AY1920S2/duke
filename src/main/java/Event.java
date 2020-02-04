import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

/**
 * Represents an Event which inherits from Task and is stored/managed by Duke
 */
public class Event extends Task {
    /**
     * Stores the time the event is supposed to take place
     */
    protected String time;
    protected Optional<LocalDate> dueTime;
    /**
     * Creates an Event object with given description and time
     * @param description
     * @param time
     */
    public Event(String description, String time) {
        super(description);
        this.time = time;
        if(this.time.matches("\\d{4}-\\d{2}-\\d{2}")) { //YYYY-MM-DD
            this.dueTime = Optional.of(LocalDate.parse(this.time));
        }
    }

    /**
     *  Gives a string representation of the Event by building upon parent's representation method
     * @return
     */
    @Override
    public String toString() {
        if (this.dueTime.isPresent()) {
            return "[E]" + super.toString() + String.format("(at: %s)",
                    this.dueTime.get().format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        } else {
            return "[E]" + super.toString() + String.format("(at: %s)", this.time);
        }

    }
}
