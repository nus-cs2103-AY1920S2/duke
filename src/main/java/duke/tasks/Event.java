package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.utilities.TimeParser;

public class Event extends Task implements TimeParser {
    protected LocalDate eventTime;

    public Event(String description, String eventTime) throws DateTimeParseException{ // constructor for creating new event
        super(description);
        this.eventTime = TimeParser.parseDate(eventTime);
        super.TYPE = TaskType.EVENT;
    }

    public Event(String status, String description, String eventTime) throws DateTimeParseException { // constructor for parsing tasks from hard disk
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