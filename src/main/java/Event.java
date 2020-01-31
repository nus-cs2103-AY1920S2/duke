import java.time.LocalDate;

/**
 * An Event task.
 */
public class Event extends DateTimeTask {

    public Event(String description, LocalDate at) {
        super(description, at);
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + this.getDateTime() + ")";
    }
}
