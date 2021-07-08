package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.utilities.TimeParser;

public class Event extends Task implements TimeParser {
    private LocalDate date;


    /**
     * Another constructor for creating Event
     * This constructor is called in the Parser class when parsing file to string, or when parsing command to task.
     * Sets isDone to true or false according to status number (0 or 1).
     *
     * @param status      0 - isDone is false, 1 - isDone is true
     * @param description description of event
     * @param date        String representation of the event time
     * @throws DateTimeParseException
     */
    public Event(String status, String description, String date) throws DateTimeParseException { // constructor for parsing tasks from hard disk
        super(TaskType.EVENT, status, description);
        this.date = TimeParser.parseDate(date);
        assert this.date != null : "event time is null after parsing in constructor";
    }

    public LocalDate getTaskTime() {
        return this.date;
    }

    /**
     * Method to change the date assigned to this event. Called in Parser class when user is updating the date.
     *
     * @param update the date, represented in String, to be changed
     * @return boolean true by default
     */
    @Override
    public boolean changeDate(String update) {
        date = TimeParser.parseDate(update);
        return true;
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + TimeParser.printDate(this.date) + ")";
    }
}