package duke.other;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a date validator to check is a date is formatted in a specified format. A DateValidator object
 * corresponds to a Date Formatter of a specific date format.
 */
public class DateValidator {
    private DateTimeFormatter dateFormatter;


    public DateValidator(DateTimeFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

    /**
     * Returns a boolean if the format of the date inserted corresponds to the Date Formatter.
     * @param dateStr String of the date to be validated
     * @return boolean of whether the format of the date corresponds to the Date Formatter
     */
    public boolean isValidDate(String dateStr) {
        try {
            LocalDate.parse(dateStr, this.dateFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns a boolean if the format of the time inserted corresponds to the Time Formatter.
     * @param timeStr String of the time to be validated
     * @return boolean of whether the format of the time corresponds to the Time Formatter
     */
    public boolean isValidTime(String timeStr) {
        try {
            LocalTime.parse(timeStr, this.dateFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}