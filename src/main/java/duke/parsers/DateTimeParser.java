package duke.parsers;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;

import duke.exceptions.DukeException;

public class DateTimeParser {
    private List<DateTimeFormatter> dtFormatters = new ArrayList<>();
    
    public DateTimeParser() {
        dtFormatters.add(DateTimeFormatter.ofPattern("dd/MM/yyyy[ HH][:][mm][:ss]"));
        dtFormatters.add(DateTimeFormatter.ofPattern("dd-MM-yyyy[ HH][:][mm][:ss]"));
        dtFormatters.add(DateTimeFormatter.ofPattern("dd MM yyyy[ HH][:][mm][:ss]"));
    }
    public LocalDateTime parse(String str) throws DukeException {
        for (DateTimeFormatter dtf : dtFormatters) {
            try {
                TemporalAccessor ta = dtf.parseBest(str, LocalDateTime::from, LocalDate::from);
                if (ta instanceof LocalDateTime) {
                    return (LocalDateTime)ta;
                  } else {
                    return ((LocalDate)ta).atStartOfDay();
                  }
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        throw new DukeException("Cannot parse datetime! Suggested format: dd/MM/yyyy HH:mm:ss");
    }
}