import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task implements TimeParser {
    protected LocalDate eventTime;

    Event(String unparsed) {
        String[] split = unparsed.split("/at");
        this.description = split[0].trim();
        this.eventTime = TimeParser.parseDate(split[1].trim());
        super.TYPE = TaskType.EVENT;
    }

    Event(String status, String description, String eventTime) {
        super(status, description);
        super.TYPE = TaskType.EVENT;
        this.eventTime = TimeParser.parseDate(eventTime);
    }

    public LocalDate getTaskTime() {
        return this.eventTime;
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.eventTime.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}