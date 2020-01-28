package jiachen.duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task {

    private LocalDateTime by;

    public DeadlineTask(String description, String deadline) throws InvalidDukeFormatException, DateTimeParseException {
        super(description);

        if (deadline.isEmpty()) {
            throw new InvalidDukeFormatException("Missing /by clause or missing by when!");
        }
        this.by = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d YYYY");
        return "[D]" + super.toString() + " (by: " + formatter.format(this.by) + ")";
    }

    @Override
    public String format() {
        return "D | " + super.format() + " | " + DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm").format(this.by);
    }
}
