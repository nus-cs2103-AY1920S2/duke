package duke.util;

import duke.exception.DuchessException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.Optional;

import static duke.util.MagicStrings.ERROR_WRONG_DATE_FORMAT;
import static duke.util.StringCleaner.cleanAndLowerString;

/**
 * The {@code DateTimeParser} is a helper class with static methods to parse
 * user provided inputs into {@code LocalDateTime} objects.
 */
public class DateTimeParser {
    /**
     * {@code DateTimeFormatter} that recognises the pattern "d-M-yy HHmm".
     */
    public static DateTimeFormatter dateTimePattern = DateTimeFormatter.ofPattern("d-M-yy HHmm");
    /**
     * {@code DateTimeFormatter} that recognises the pattern "d-M-yy".
     */
    public static DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("d-M-yy");

    /**
     * Returns a {@code LocalDateTime} object based on the given
     * {@code dateTimeString}.
     *
     * <p>Allowed formats for {@code dateTimeString} are days of the week e.g.
     * "Monday"; relative timings e.g. "Today", "Tonight"; of datetime format
     * "d-M-yy" or "d-M-yy HHmm".
     *
     * @param dateTimeString Cleaned user provided input in {@code String} format.
     * @return {@code LocalDateTime} object based on given {@code dateTimeString}.
     * @throws DuchessException If {@code dateTimeString} is not of the correct
     *                          format.
     */
    public static LocalDateTime parseDateTime(String dateTimeString) throws DuchessException {
        assert dateTimeString.equals(cleanAndLowerString(dateTimeString));
        return getDateTimeFromWords(dateTimeString)
                .or(() -> getDateTimeUsingDateTimePattern(dateTimeString)
                        .or(() -> getDateTimeUsingDatePattern(dateTimeString)))
                .orElseThrow(() -> new DuchessException(ERROR_WRONG_DATE_FORMAT));
    }

    private static Optional<LocalDateTime> getDateTimeFromWords(String dateTimeString) {
        switch (dateTimeString) {
        case "today":
            return Optional.of(LocalDate.now().atTime(17, 0));
        case "tonight":
            return Optional.of(LocalDate.now().atTime(21, 0));
        case "tmr":
            // Fallthrough
        case "tomorrow":
            return Optional.of(LocalDate.now().plusDays(1).atTime(17, 0));
        default:
            try {
                return Optional.of(LocalDate.now()
                        .with(TemporalAdjusters.next(DayOfWeek.valueOf(
                                extendDayOfWeek(dateTimeString).toUpperCase()))).atTime(17, 0));
            } catch (IllegalArgumentException e) {
                return Optional.empty();
            }
        }
    }

    private static String extendDayOfWeek(String dateTimeString) {
        if (dateTimeString.endsWith("day")) {
            return dateTimeString;
        }
        return dateTimeString + "day";
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
