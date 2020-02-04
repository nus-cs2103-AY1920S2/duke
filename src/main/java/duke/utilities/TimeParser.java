package duke.utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
public interface TimeParser {
    static LocalDate parseDate(String date) throws DateTimeParseException {
        try {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern("d/M/yyyy"));
        }
        catch(DateTimeParseException e) {
            return LocalDate.parse(date); // parse the default pattern
        }
    }


    static String printDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }
}
