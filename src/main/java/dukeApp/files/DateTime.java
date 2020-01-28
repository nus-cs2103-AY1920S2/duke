package dukeapp.files;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTime {
    protected String date;
    protected String time;

    public DateTime() {
    }

    /**
     * Convert the date string into a date format
     * @param date date in String
     * @return date of a specific format
     */
    public String convertDate(String date) {
        LocalDate d = LocalDate.parse(date);
        return d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Convert the time string into time format
     * @param time time in String
     * @return time of a specific format
     */
    public String convertTime(String time) {
        LocalTime l = LocalTime.parse(time);
        return l.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}
