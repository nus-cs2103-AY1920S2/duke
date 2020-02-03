import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Events extends Task {

    protected String at;
    protected LocalDateTime dateTime;

    public Events(String description, String at) {
        super(description);
        this.at = at;

        dateTime = LocalDateTime.parse(at);
    }

    public String getType() { return "E"; }

    public String getDetails() { return at; }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                .format(dateTime) + ")";
    }
}