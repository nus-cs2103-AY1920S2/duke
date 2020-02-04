package jiachen.duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {

    private String by;

    public DeadlineTask(String description, String deadline) throws InvalidDukeFormatException {
        super(description);
        this.by = deadline;

        if (deadline.isEmpty()) {
            throw new InvalidDukeFormatException("Missing /by clause or missing by when!");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    @Override
    public String format() {
        return "D | " + super.format() + " | " + LocalDateTime.parse(this.by, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }
}
