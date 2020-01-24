import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    public final LocalDateTime dateTime;
    public static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
    public static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");

    public Event(String name, String dateTime) {
        super(name);
        this.dateTime = LocalDateTime.parse(dateTime, inputFormatter);
    }

    public Event(String name, boolean completed, String dateTime) {
        super(name, completed);
        this.dateTime = LocalDateTime.parse(dateTime, inputFormatter);
    }
    
    public Event(String name, boolean completed,LocalDateTime dateTime) {
        super(name, completed);
        this.dateTime = dateTime;
    }

    @Override
    public Event complete() {
        return new Event(this.name, true, this.dateTime);
    }

    @Override
    public String toString() {
        String doneCheck = "[✓] ";
        String notDoneCheck = "[✗] ";

        if (completed) {
            return "[E]" + doneCheck + this.name + " (at: " + dateTime.format(outputFormatter) + ")";
        } else {
            return "[E]" + notDoneCheck + this.name + " (at: " + dateTime.format(outputFormatter) + ")";
        }
    }
}