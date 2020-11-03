package duke.tasks;

import java.time.LocalDateTime;

/**
 * Represents a Task that has a specific start time and end time.
 */
public class Event extends TimedTask {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Initialises an Event with the given name, start time and end time.
     * @param name Name of Event.
     * @param start Start of Event.
     * @param end End of Event.
     */
    public Event(String name, LocalDateTime start, LocalDateTime end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void reschedule(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s to %s)", getStatus(), name, formatTime(start), formatTime(end));
    }
}