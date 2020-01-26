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
 * A wrapper around multiple <code>DateTimeFormatter</code>s that parses
 * user-entered dates/times
 */
public class DateTimeParser {
    private List<DateTimeFormatter> dtFormatters = new ArrayList<>();

    public DateTimeParser() {
        dtFormatters.add(DateTimeFormatter.ofPattern("dd/MM/yyyy[ HH][:][mm][:ss]"));
        dtFormatters.add(DateTimeFormatter.ofPattern("dd-MM-yyyy[ HH][:][mm][:ss]"));
        dtFormatters.add(DateTimeFormatter.ofPattern("dd MM yyyy[ HH][:][mm][:ss]"));
    }

<<<<<<< HEAD
    /**
     * Returns a <code>LocalDateTime</code> parsed from user-entered string.
     * If no time is specified, it is automatically set to start of day.
     * 
     * @param str User-entered string representing date/time.
     * @throws DukeException If unable to parse string.
     * @return <code>LocalDateTime</code> parsed from user input.
     */
=======
>>>>>>> branch-A-CodingStandard
    public LocalDateTime parse(String str) throws DukeException {
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