package dukebot.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

/**
 * Class containing DateTime parser.
 */
public class DateTimeParse {

    private static String[] dateTimeRegexps = {
        "^\\d{4}/\\d{1,2}/\\d{1,2}$", // yyyy/M/d
        "^\\d{1,2}/\\d{1,2}/\\d{4}$", // d/M/yyyy
        "^\\d{1,2}/\\d{1,2}$", // d/M
        "^\\d{1,2}/\\d{1,2}/\\d{4} \\d{4}$", // d/M/yyyy HHmm
        "^\\d{1,2}/\\d{1,2} \\d{4}$", // d/M HHmm
        "^\\d{4}$", // HHmm
        "^\\d{4}/\\d{1,2}/\\d{1,2} \\d{4}$" // yyyy/M/d HHmm
    };

    private static DateTimeFormatter getDateTimeFormatter(int index, LocalDateTime defaultLocalDateTime) {
        switch (index) {
        case 0:
            return new DateTimeFormatterBuilder()
                    .appendPattern("yyyy/M/d")
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .toFormatter();
        case 1:
            return new DateTimeFormatterBuilder()
                    .appendPattern("d/M/yyyy")
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .toFormatter();
        case 2:
            return new DateTimeFormatterBuilder()
                    .appendPattern("d/M")
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .parseDefaulting(ChronoField.YEAR, defaultLocalDateTime.getYear())
                    .toFormatter();
        case 3:
            return DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        case 4:
            return new DateTimeFormatterBuilder()
                    .appendPattern("d/M HHmm")
                    .parseDefaulting(ChronoField.YEAR, defaultLocalDateTime.getYear())
                    .toFormatter();
        case 5:
            return new DateTimeFormatterBuilder()
                    .appendPattern("HHmm")
                    .parseDefaulting(ChronoField.DAY_OF_MONTH, defaultLocalDateTime.getDayOfMonth())
                    .parseDefaulting(ChronoField.MONTH_OF_YEAR, defaultLocalDateTime.getMonthValue())
                    .parseDefaulting(ChronoField.YEAR, defaultLocalDateTime.getYear())
                    .toFormatter();
        case 6:
            return DateTimeFormatter.ofPattern("yyyy/M/d HHmm");
        default:
            // default should never be triggered but just in case.
            assert false;
            return DateTimeFormatter.ofPattern("yyyy/M/d HHmm");
        }
    }

    /**
     * Parse the input string as a LocalDateTime if possible.
     *
     * @param dateTime String to be parsed.
     * @param localDateTime Default date to use.
     * @return The parsed string as a LocalDateTime.
     * @throws DateTimeParseException if string unable to be parsed.
     */
    public static LocalDateTime parseDateWithCustomDateTime(String dateTime, LocalDateTime localDateTime)
            throws DateTimeParseException {
        dateTime = dateTime.replaceAll("[\\\\/\\-]+", "/");
        dateTime = dateTime.replaceAll(":", "");
        for (int i = 0; i < dateTimeRegexps.length; i++) {
            if (dateTime.matches(dateTimeRegexps[i])) {
                try {
                    return LocalDateTime.parse(dateTime, getDateTimeFormatter(i, localDateTime));
                } catch (DateTimeParseException e) {
                    // There's a chance that it passes regex but is still invalid date.
                    // System.out.println(e);
                }
            }
        }
        // This throws a DateTimeParseException
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy/M/d HHmm"));
    }

    /**
     * Parse the input string as a LocalDateTime if possible.
     *
     * @param dateTime String to be parsed.
     * @return The parsed string as a LocalDateTime.
     * @throws DateTimeParseException if string unable to be parsed.
     */
    public static LocalDateTime parseDate(String dateTime) throws DateTimeParseException {
        return parseDateWithCustomDateTime(dateTime, LocalDateTime.now());
    }

}