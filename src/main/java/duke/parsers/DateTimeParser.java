package duke.parsers;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;

import duke.exceptions.DukeException;

/**
 * A wrapper around multiple DateTimeFormatters that parses user-entered date/times.
 */
public class DateTimeParser {
    private List<DateTimeFormatter> dtFormatters = new ArrayList<>();

    /**
     * Initialises a DateTimeParser.
     */
    public DateTimeParser() {
        dtFormatters.add(DateTimeFormatter.ofPattern("dd/MM/yyyy[ HH][:][mm][:ss]"));
        dtFormatters.add(DateTimeFormatter.ofPattern("dd-MM-yyyy[ HH][:][mm][:ss]"));
        dtFormatters.add(DateTimeFormatter.ofPattern("dd MM yyyy[ HH][:][mm][:ss]"));
    }

    /**
     * Returns a LocalDateTime parsed from user-entered string. If no time is
     * specified, it is automatically set to start of day.
     * 
     * @param str User-entered string representing date/time.
     * @return LocalDateTime parsed from user input.
     * @throws DukeException If unable to parse string.
     */
    public LocalDateTime parse(String str) throws DukeException {
        assert dtFormatters.size() > 0 : "There should be at least one DateTimeFormatter";
        for (DateTimeFormatter dtf : dtFormatters) {
            try {
                TemporalAccessor ta = dtf.parseBest(str, LocalDateTime::from, LocalDate::from);
                if (ta instanceof LocalDateTime) {
                    return (LocalDateTime) ta;
                } else {
                    return ((LocalDate) ta).atStartOfDay();
                }
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        throw new DukeException("Cannot parse datetime! Suggested format: dd/MM/yyyy HH:mm:ss");
    }
}