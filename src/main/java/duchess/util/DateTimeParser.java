package duchess.util;

import duchess.exception.DuchessException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.Optional;

/**
 * The {@code DateTimeParser} is a helper class with static
 * methods to parse user provided inputs into {@code LocalDateTime}
 * objects.
 */
public class DateTimeParser {
    /**
     * {@code DateTimeFormatter} that recognises the pattern "d/M/yy HHmm".
     */
    public static DateTimeFormatter dateTimePattern = DateTimeFormatter.ofPattern("d/M/yy HHmm");
    /**
     * {@code DateTimeFormatter} that recognises the pattern "d/M/yy".
     */
    public static DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("d/M/yy");

    /**
     * Returns a {@code LocalDateTime} object based on the given {@code dateTimeString}.
     * <p>
     * Allowed formats for {@code dateTimeString} are days of the week e.g. "Monday";
     * relative timings e.g. "Today", "Tonight"; of datetime format "d/M/yy" or "d/M/yy HHmm".
     *
     * @param dateTimeString User provided input in {@code String} format.
     * @return {@code LocalDateTime} object based on given {@code dateTimeString}.
     * @throws DuchessException If {@code dateTimeString} is not of the correct format.
     */
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
