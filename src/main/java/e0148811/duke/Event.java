package e0148811.duke;

import java.time.LocalDate;

public class Event extends Task {
    private LocalDate time;

    public Event(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    public Event(boolean isDone, String description, String time) {
        super(isDone, description);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ", " + time.getDayOfWeek() + ")";
    }

    @Override
    public String toSimplerString() {
        return "E//" + super.toSimplerString() + "//" + time;
    }
}
