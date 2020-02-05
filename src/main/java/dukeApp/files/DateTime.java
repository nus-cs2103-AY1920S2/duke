package dukeApp.files;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTime {

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
}
