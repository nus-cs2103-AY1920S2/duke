package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private LocalDateTime time;

    public Deadline(String instruction, LocalDateTime time) {
        super(instruction);
        this.time = time;
    }

    public LocalDateTime getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + time.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
