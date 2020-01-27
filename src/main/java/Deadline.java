import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    private String getFormattedDate() {
        return this.by.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
    }
    
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.getFormattedDate());
    }
}
