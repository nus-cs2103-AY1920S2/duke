import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        String formatDateTime = by.format(DateTimeFormatter.ofPattern("dd LLL yyyy HH:mma"));
        return "[D]" + super.toString() + " (by: " + formatDateTime + ")";
    }
}
