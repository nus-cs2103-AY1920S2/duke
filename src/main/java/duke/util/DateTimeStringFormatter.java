package duke.util;

import static duke.util.MagicStrings.BLANK;
import static duke.util.MagicStrings.DATE_TIME_OVERDUE;
import static duke.util.MagicStrings.DATE_TIME_TODAY;
import static duke.util.MagicStrings.DATE_TIME_TOMORROW;
import static duke.util.MagicStrings.DATE_TIME_YESTERDAY;
import static java.time.temporal.ChronoUnit.DAYS;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The {@code DateTimeStringFormatter} is a helper class with
 * static methods to format {@code LocalDateTime} objects into
 * meaningful {@code String} formats. This is designed to be used
 * with deadlines, as the "[OVERDUE]" label is returned as well
 * should the given {@code LocalDateTime} object be before the
 * current datetime.
 *
 * <p>An example would be "Today" if the given {@code LocalDateTime}
 * object is dated today.
 */
public class DateTimeStringFormatter {
    private static final DateTimeFormatter HOUR_MINUTES = DateTimeFormatter.ofPattern("h:mm a");
    private static final DateTimeFormatter MONTH_HOUR_MINUTES = DateTimeFormatter.ofPattern("MMM d h:mm a");
    private static final DateTimeFormatter MONTH_YEAR_HOUR_MINUTES = DateTimeFormatter.ofPattern("MMM d yyyy h:mm a");
    private static final DateTimeFormatter WEEKDAY_HOUR_MINUTES = DateTimeFormatter.ofPattern("EEE h:mm a");

    /**
     * Formats {@code LocalDateTime} objects into meaningful {@code String}s.
     *
     * @param dateTime    {@code LocalDateTime} object representing the deadline.
     * @param isCompleted Whether the task has been completed.
     * @return Meaningfully formatted {@code String}.
     */
    public static String formatDateTime(LocalDateTime dateTime, boolean isCompleted) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        long differenceInDays = DAYS.between(dateTime.toLocalDate(), currentDateTime.toLocalDate());
        boolean isToday = differenceInDays == 0;
        boolean isOneDayDifference = Math.abs(differenceInDays) == 1;
        boolean isDifferentYear = dateTime.getYear() != currentDateTime.getYear();
        if (dateTime.isBefore(currentDateTime)) {
            if (isToday) {
                return lowerAmPm(DATE_TIME_TODAY + dateTime.format(HOUR_MINUTES)
                        + (isCompleted ? BLANK : DATE_TIME_OVERDUE));

            } else if (isOneDayDifference) {
                // isYesterday
                return lowerAmPm(DATE_TIME_YESTERDAY + dateTime.format(HOUR_MINUTES)
                        + (isCompleted ? BLANK : DATE_TIME_OVERDUE));
            } else if (isDifferentYear) {
                return lowerAmPm(dateTime.format(MONTH_YEAR_HOUR_MINUTES) + (isCompleted ? BLANK : DATE_TIME_OVERDUE));
            }
            return lowerAmPm(dateTime.format(MONTH_HOUR_MINUTES) + (isCompleted ? BLANK : DATE_TIME_OVERDUE));
        }

        boolean isThisWeek = differenceInDays > -7;
        if (isToday) {
            return lowerAmPm(DATE_TIME_TODAY + dateTime.format(HOUR_MINUTES));
        } else if (isOneDayDifference) {
            // isTomorrow
            return lowerAmPm(DATE_TIME_TOMORROW + dateTime.format(HOUR_MINUTES));
        } else if (isThisWeek) {
            return lowerAmPm(dateTime.format(WEEKDAY_HOUR_MINUTES));
        } else if (isDifferentYear) {
            return lowerAmPm(dateTime.format(MONTH_YEAR_HOUR_MINUTES));
        }
        return lowerAmPm(dateTime.format(MONTH_HOUR_MINUTES));
    }

    private static String lowerAmPm(String dateTimeString) {
        return dateTimeString.replace("AM", "am").replace("PM", "pm");
    }
}
