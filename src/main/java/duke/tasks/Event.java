package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.utilities.TimeParser;

public class Event extends Task implements TimeParser {
    protected LocalDate eventTime;


    /**
     * Another constructor for creating Event
     * This constructor is called in the Parser class when parsing file to string, or when parsing command to task.
     * Sets isDone to true or false according to status number (0 or 1).
     *
     * @param status      0 - isDone is false, 1 - isDone is true
     * @param description description of event
     * @param eventTime   String representation of the event time
     * @throws DateTimeParseException
     */
    public Event(String status, String description, String eventTime) throws DateTimeParseException { // constructor for parsing tasks from hard disk
        super(TaskType.EVENT, status, description);
        this.eventTime = TimeParser.parseDate(eventTime);
        assert this.eventTime != null : "event time is null after parsing in constructor";
    }

    public LocalDate getTaskTime() {
        return this.eventTime;
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + TimeParser.printDate(this.eventTime) + ")";
    }
}