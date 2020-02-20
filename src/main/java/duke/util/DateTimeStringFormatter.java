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
                return (DATE_TIME_TODAY + dateTime.format(HOUR_MINUTES)
                        + (isCompleted ? BLANK : DATE_TIME_OVERDUE)).replace("AM", "am").replace("PM", "pm");

            } else if (isOneDayDifference) {
                // isYesterday
                return (DATE_TIME_YESTERDAY + dateTime.format(HOUR_MINUTES)
                        + (isCompleted ? BLANK : DATE_TIME_OVERDUE)).replace("AM", "am").replace("PM", "pm");
            } else if (isDifferentYear) {
                return (dateTime.format(MONTH_YEAR_HOUR_MINUTES)
                        + (isCompleted ? BLANK : DATE_TIME_OVERDUE)).replace("AM", "am").replace("PM", "pm");
            }
            return (dateTime.format(MONTH_HOUR_MINUTES)
                    + (isCompleted ? BLANK : DATE_TIME_OVERDUE)).replace("AM", "am").replace("PM", "pm");
        }

        boolean isThisWeek = differenceInDays > -7;
        if (isToday) {
            return (DATE_TIME_TODAY + dateTime.format(HOUR_MINUTES)).replace("AM", "am").replace("PM", "pm");
        } else if (isOneDayDifference) {
            // isTomorrow
            return (DATE_TIME_TOMORROW + dateTime.format(HOUR_MINUTES)).replace("AM", "am").replace("PM", "pm");
        } else if (isThisWeek) {
            return (dateTime.format(WEEKDAY_HOUR_MINUTES)).replace("AM", "am").replace("PM", "pm");
        } else if (isDifferentYear) {
            return (dateTime.format(MONTH_YEAR_HOUR_MINUTES)).replace("AM", "am").replace("PM", "pm");
        }
        return (dateTime.format(MONTH_HOUR_MINUTES)).replace("AM", "am").replace("PM", "pm");


    }
}
