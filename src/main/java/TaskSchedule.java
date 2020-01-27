import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TaskSchedule {
    LocalDateTime date;
    private static final String inputDateFormat = "dd-MM-yyyy HH:mm";

    private TaskSchedule(LocalDateTime date) {
        this.date = date;
    }

    public static TaskSchedule parseSchedule(String scheduleString) throws DukeInvalidDateFormatException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(inputDateFormat);
            LocalDateTime date = LocalDateTime.parse(scheduleString, formatter);
            return new TaskSchedule(date);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateFormatException("OOPS! It seems that your date " +
                    "is not properly formatted. The date should be in form of 'dd-MM-yyyy HH:mm'");
        }
    }
}
