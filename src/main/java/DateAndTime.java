import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateAndTime {

    private LocalDate date;

    public DateAndTime(String dateInString) {
        this.date = LocalDate.parse(dateInString);
    }

    public String formatDateToString() {
        return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

}
