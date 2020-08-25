package liaomeng.duke;

import java.time.LocalDate;

/**
 * A task that has a date indicating when it occurs.
 */
public class Event extends Task {
    private LocalDate time;

    /**
     * Creates an event task.
     *
     * @param isDone boolean indicating whether the event is marked as done.
     * @param description string description of the event.
     * @param time date of the event occurring.
     * @param level priority level of the event.
     */
    public Event(boolean isDone, String description, LocalDate time, PriorityLevel level) {
        super(isDone, description, level);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "\n    (at: " + time + ", " + time.getDayOfWeek() + ")";
    }

    @Override
    public String toSimplerString() {
        return "E//" + super.toSimplerString() + "//" + time;
    }
}
