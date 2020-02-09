package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The type Date and time.
 */
public class DateAndTime {

    private LocalDate date;

    /**
     * Instantiates a new Date and time.
     *
     * @param dateInString the date in string
     */
    public DateAndTime(String dateInString) {
        this.date = LocalDate.parse(dateInString);
    }

    /**
     * Formats date to string.
     *
     * @return the string
     */
    public String formatDateToString() {
        return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

}
