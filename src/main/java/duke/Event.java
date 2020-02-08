package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event.
 */
public class Event extends Task {
    LocalDateTime time;

    public Event(String instr, LocalDateTime time) {
        super(instr);
        super.type = "EVENT";
        this.time = time;
    }

    /**
     * Getter for the <code>time</code> of <code>Event</code>.
     */
    public LocalDateTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        if (super.done) {
            return "[E][\u2713] " + super.instr;
        } else {
            return "[E][\u2718] " + super.instr + " (at: "
                    + time.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"))
                    + ")";
        }
    }
}