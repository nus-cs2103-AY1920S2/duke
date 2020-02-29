package duke.tasks;

import java.time.LocalDateTime;

/**
 * Represents a Task that has a specific deadline to be met.
 */
public class Deadline extends TimedTask {
    private LocalDateTime dateTime;

    public Deadline(String name, LocalDateTime dateTime) {
        super(name);
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void reschedule(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", getStatus(), name, formatTime(dateTime));
    }
}