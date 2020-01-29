package e0148811.duke;

import java.time.LocalDate;

public class Event extends Task {
    private LocalDate time;

    public Event(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ", " + time.getDayOfWeek() + ")";
    }
}
