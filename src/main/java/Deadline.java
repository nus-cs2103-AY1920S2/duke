import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String desc, String by) {
        super(desc);
        // by variable must be in YYYY-mm-dd
        this.by = LocalDate.parse(by);
    }

    private String formatDate() {
        return by.format(DateTimeFormatter.ofPattern("dd MMM yy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDate() + ")";
    }
}
