package dukeApp.files;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTime {
    protected String date;
    protected String time;

    public DateTime() {
    }

    public String convertDate(String date) {
        LocalDate d = LocalDate.parse(date);
        return d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String convertTime(String time) {
        LocalTime l = LocalTime.parse(time);
        return l.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}
