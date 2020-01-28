import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected static String dateTimePattern = "MMM d yyyy HH:mm";
    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Event.dateTimePattern);
    protected LocalDateTime dateTime;

    public static LocalDateTime parseDateTime(String inputDateTime) throws DateTimeParseException {
        String inputDateTimePattern = "yyyy-MM-dd HHmm";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(inputDateTimePattern);
        LocalDateTime localDateTime = LocalDateTime.parse(inputDateTime, dateTimeFormatter);
        return localDateTime;
    }

    public Event(String taskTitle, LocalDateTime dateTime) {
        super(taskTitle);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateTimeFormatter.format(dateTime) + ")";
    }

    public String getDateTime() {
        return dateTimeFormatter.format(dateTime);
    }
}
