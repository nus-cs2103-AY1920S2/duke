package duke.utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * An interface to parse date and time.
 * Implemented by Deadline and Event classes.
 */
public interface TimeParser {
    /**
     * Parses given string representation of date.
     * Allows for 2 formats, one for parsing user input in the format "d/M/yyyy".
     * The second format is for parsing dates in the default LocalDate pattern from file.
     *
     * @param date String representation of date
     * @return LocalDate object representing the given date
     * @throws DateTimeParseException if no valid date format is given
     */
    static LocalDate parseDate(String date) throws DateTimeParseException {
        try {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern("d/M/yyyy"));
        } catch (DateTimeParseException e) {
            return LocalDate.parse(date); // parse the default pattern
        }
    }

    /**
     * Prints the date in the format "d MMM yyyy".
     * Called in the Deadline and Event classes when displaying to the user.
     *
     * @param date the LocalDate object to be printed
     * @return String representation "d MMM yyyy"
     */
    static String printDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }
}
