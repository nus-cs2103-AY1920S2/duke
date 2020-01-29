package duchess.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.ChronoUnit.DAYS;

public class DateTimeStringFormatter {
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
