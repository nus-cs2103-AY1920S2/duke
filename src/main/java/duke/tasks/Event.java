package duke.tasks;

import java.time.LocalDateTime;

public class Event extends TimedTask {
    protected LocalDateTime start;
    protected LocalDateTime end;

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

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s to %s)", getStatus(), name, formatTime(start), formatTime(end));
    }
}