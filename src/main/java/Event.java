import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Item {

    private LocalDateTime time;

    /**
     * Constructor of the event object.
     * @param name The event name.
     * @param time The event date/time.
     */
    public Event(String name, LocalDateTime time) {
        super(name);
        this.time = time;
    }

    public Event(String name, LocalDateTime time, boolean done) {
        super(name, done);
        this.time = time;
    }

    public String toSaveFormat() {
        return String.format("E | %s | %s", super.toSaveFormat(), time);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
            time.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")));
    }
}