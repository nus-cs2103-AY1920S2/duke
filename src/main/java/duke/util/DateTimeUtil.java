package duke.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/**
 * Contains Date-Time related method(s).
 *
 * @author  Hardy Shein
 * @version 0.1
 */
public class DateTimeUtil {
    private static DateTimeFormatter formatter = null;

    /**
     * A conversion tool from String to LocalDateTime.
     * @param dateTimeString to be converted to LocalDateTime object
     * @return the LocalDateTime representation of the input string
     */
    public static LocalDateTime stringAsDateTime(String dateTimeString) {
        if (formatter == null) {
            formatter = DateTimeFormatter
                    .ofPattern("yyyy-MM-dd[ HH:mm]");
        }
        TemporalAccessor temporalAccessor = formatter.parseBest(dateTimeString, LocalDateTime::from, LocalDate::from);
        return (temporalAccessor instanceof LocalDateTime)
                ? (LocalDateTime)temporalAccessor :
                ((LocalDate)temporalAccessor).atStartOfDay(); // Assume time is 00:00 where no time is given
    }
}