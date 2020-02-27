//package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime time;

    public Event(String instruction, LocalDateTime time) {
        super(instruction);
        this.time = time;
    }

    public LocalDateTime getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + time.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
