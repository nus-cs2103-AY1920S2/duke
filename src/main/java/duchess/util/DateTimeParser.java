package duchess.util;

import duchess.exception.DuchessException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.Optional;

public class DateTimeParser {
    public static DateTimeFormatter dateTimePattern = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    public static DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("d/M/yyyy");

    public static LocalDateTime parseDateTime(String dateTimeString) throws DuchessException {
        return getDateTimeFromWords(dateTimeString)
                .or(() -> getDateTimeUsingDateTimePattern(dateTimeString)
                        .or(() -> getDateTimeUsingDatePattern(dateTimeString)))
                .orElseThrow(() -> new DuchessException("Your input is of the wrong format.\n"
                        + "\tType help to view the accepted formats."));
    }

    private static Optional<LocalDateTime> getDateTimeFromWords(String dateTimeString) {
        switch (dateTimeString) {
        case "today":
            return Optional.of(LocalDate.now().atTime(17, 0));
        case "tonight":
            return Optional.of(LocalDate.now().atTime(21, 0));
        case "tomorrow":
            return Optional.of(LocalDate.now().plusDays(1).atTime(17, 0));
        case "monday":
            // Fallthrough
        case "tuesday":
            // Fallthrough
        case "wednesday":
            // Fallthrough
        case "thursday":
            // Fallthrough
        case "friday":
            // Fallthrough
        case "saturday":
            // Fallthrough
        case "sunday":
            return Optional.of(LocalDate.now()
                    .with(TemporalAdjusters.next(DayOfWeek.valueOf(dateTimeString.toUpperCase())))
                    .atTime(17, 0));
        default:
            return Optional.empty();
        }
    }

    private static Optional<LocalDateTime> getDateTimeUsingDateTimePattern(String dateTimeString) {
        try {
            return Optional.of(LocalDateTime.parse(dateTimeString, dateTimePattern));
        } catch (DateTimeParseException e) {
            return Optional.empty();
        }
    }

    private static Optional<LocalDateTime> getDateTimeUsingDatePattern(String dateTimeString) {
        try {
            return Optional.of(LocalDate.parse(dateTimeString, datePattern).atTime(17, 0));
        } catch (DateTimeParseException e) {
            return Optional.empty();
        }
    }
}
