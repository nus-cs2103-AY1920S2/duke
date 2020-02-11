package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate duration;

    public Event(String desc, LocalDate duration) {
        super(desc);
        this.duration = duration;
    }

    /**
     * Constructs a new Event task from a description, duration and status.
     *
     * @param desc     The description of the Event
     * @param duration The duration of the Event
     * @param isDone   The status of the Event
     */
    public Event(String desc, LocalDate duration, boolean isDone) {
        super(desc);
        super.setStatus(isDone);
        this.duration = duration;

    }

    @Override
    public String generateSaveFileEntry() {
        return String.format("E | %d | %s | %s\n", this.getStatusAsInt(), this.getDescription(),
                this.duration.toString());
    }

    @Override
    public String toString() {
        return String.format("[E] [%s] %s (at: %s)", this.getStatus(), this.getDescription(),
                this.duration.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}