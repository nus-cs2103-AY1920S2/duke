import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

abstract class DateTimeTask extends Task {
    protected LocalDate dateTime;

    public DateTimeTask(String description, LocalDate dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return this.dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
