import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

public class Event extends Task {
    protected String at = "";
    protected LocalDate date;

    public Event(String description, String at) {
        super(description);
        try {
            date = LocalDate.parse(at);
            System.out.println("Parsed date successfully");
        } catch(DateTimeParseException de) {
            this.at = at;
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: "
                + (at.isEmpty()?date.format(DateTimeFormatter
                    .ofLocalizedDate(FormatStyle.LONG)):this.at) + ")";
    }
}
