import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidator {
    private DateTimeFormatter dateFormatter;

    public DateValidator(DateTimeFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

    public boolean isValidDate(String dateStr) {
        try {
            LocalDate.parse(dateStr, this.dateFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public boolean isValidTime(String timeStr) {
        try {
            LocalTime.parse(timeStr, this.dateFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}