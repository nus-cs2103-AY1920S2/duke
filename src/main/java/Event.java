import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Item {

    private LocalDateTime time;

    /**
     * Contstructor of the event object.
     * @param name The event name.
     * @param time The event date/time.
     */
    public Event(String name, LocalDateTime time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
            time.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")));
    }
}