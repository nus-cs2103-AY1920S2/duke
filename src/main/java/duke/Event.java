package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate duration;

    public Event(String desc, LocalDate duration) {
        super(desc);
        this.duration = duration;
    }

    public Event(String desc, LocalDate duration, boolean isDone) {
        super(desc);
        super.setStatus(isDone);
        this.duration = duration;

    }

    @Override
    public String generateSaveFileEntry() {
        return String.format("E | %d | %s | %s\n", this.getStatusAsInt(), this.getDescription(), this.duration.toString());
    }

    @Override
    public String toString() {
        return String.format("[E] [%s] %s (at: %s)", this.getStatus(), this.getDescription(),
                this.duration.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}