import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {

    protected String by;
    protected LocalDateTime dateTime;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;

        dateTime = LocalDateTime.parse(by);
    }

    public String getType() { return "D"; }

    public String getDetails() { return by; }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                .format(dateTime) + ")";
    }
}