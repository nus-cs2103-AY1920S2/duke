import java.time.LocalDate;

public class Event extends Task implements TimeParser {
    protected LocalDate eventTime;

    Event(String description, String eventTime) { // constructor for creating new event
        super(description);
        this.eventTime = TimeParser.parseDate(eventTime);
        super.TYPE = TaskType.EVENT;
    }

    Event(String status, String description, String eventTime) { // constructor for parsing tasks from hard disk
        super(status, description);
        super.TYPE = TaskType.EVENT;
        this.eventTime = TimeParser.parseDate(eventTime);
    }

    public LocalDate getTaskTime() {
        return this.eventTime;
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + TimeParser.printDate(this.eventTime)  + ")";
    }
}