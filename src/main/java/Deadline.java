import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

public class Deadline extends Task {
    protected String by = "";
    protected LocalDate date;

    public Deadline(String description, String by) {
        super(description);
        try {
            this.date = LocalDate.parse(by);
        } catch(DateTimeParseException de) {
            this.by = by;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "
                + (by.isEmpty()?date.format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.LONG)):this.by) + ")";
    }
}
