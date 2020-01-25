import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String by;
    protected LocalDateTime dateTime;
    private DateTimeFormatter defaultFormatter = DateTimeFormatter.ofPattern("yyyy MM dd");

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.dateTime = LocalDateTime.parse(by, defaultFormatter);;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTime.toString() + ")";
    }
}
