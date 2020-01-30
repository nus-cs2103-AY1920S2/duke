import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {
    protected String dayTime;

    public Deadline(String description, String dayTime) {
        super(description);
        if (dayTime.split(" ").length >= 2) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            LocalDateTime dt = LocalDateTime.parse(dayTime, formatter);
            this.dayTime = dt.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT));
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate d = LocalDate.parse(dayTime, formatter);
            this.dayTime = d.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
        }
    }

    public String getDayTime() {
        return dayTime;
    }
}
