package duchess.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.ChronoUnit.DAYS;

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
        if (dateTime.isBefore(currentDateTime)) {
            if (differenceInDays == 0) {
                return "Today "
                        + dateTime.format(DateTimeFormatter.ofPattern("h:mm a"))
                        + (isCompleted ? "" : " [OVERDUE]");
            } else {
                if (differenceInDays == 1) {
                    return "Yesterday "
                            + dateTime.format(DateTimeFormatter.ofPattern("h:mm a"))
                            + (isCompleted ? "" : " [OVERDUE]");
                }
                if (dateTime.getYear() != currentDateTime.getYear()) {
                    return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a"))
                            + (isCompleted ? "" : " [OVERDUE]");
                }
                return dateTime.format(DateTimeFormatter.ofPattern("MMM d h:mm a"))
                        + (isCompleted ? "" : " [OVERDUE]");
            }
        } else {
            if (differenceInDays == 0) {
                return "Today " + dateTime.format(DateTimeFormatter.ofPattern("h:mm a"));
            } else if (differenceInDays == -1) {
                return "Tomorrow " + dateTime.format(DateTimeFormatter.ofPattern("h:mm a"));
            } else if (differenceInDays > -7) {
                return dateTime.format(DateTimeFormatter.ofPattern("EEE h:mm a"));
            } else {
                if (dateTime.getYear() != currentDateTime.getYear()) {
                    return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a"));
                }
                return dateTime.format(DateTimeFormatter.ofPattern("MMM d h:mm a"));
            }
        }
    }
}
