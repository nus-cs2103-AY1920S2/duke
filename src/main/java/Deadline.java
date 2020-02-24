import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String taskName, LocalDateTime by, int done) {
        super("deadline", done, taskName);
        this.by = by;
    }

    LocalDateTime getTime() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String formatDateTime = by.format(format);
        return by; //return the localDateTime
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm");
        String formatDateTime = by.format(format);
        return "[D]" + super.toString() + " (by: " + formatDateTime + ")";
    }
}
