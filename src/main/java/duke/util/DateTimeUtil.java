package duke.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class DateTimeUtil {
    private static DateTimeFormatter formatter = null;

    /**
     * A conversion tool from String to LocalDateTime.
     * @param dt_string to be converted to LocalDateTime object
     * @return the LocalDateTime representation of the input string
     */
    public static LocalDateTime stringAsDateTime(String dt_string) {
        if (formatter == null) {
            formatter = DateTimeFormatter
                    .ofPattern("yyyy-MM-dd[ HH:mm]");
        }
        TemporalAccessor tA = formatter.parseBest(dt_string, LocalDateTime::from, LocalDate::from);
        return (tA instanceof LocalDateTime ) ?
                (LocalDateTime)tA :
                ((LocalDate)tA).atStartOfDay(); // Assume time is 00:00 where no time is given
    }
}