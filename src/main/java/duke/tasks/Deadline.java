package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.utilities.TimeParser;

public class Deadline extends Task implements TimeParser {
    private LocalDate date;

    /**
     * Another constructor for creating Deadline.
     * This constructor is called in the Parser class when parsing file to string.
     * Sets isDone to true or false according to status number (0 or 1)
     *
     * @param status      0 - isDone is false, 1 - isDone is true
     * @param description description of date
     * @param date        String representation of the date
     * @throws DateTimeParseException
     */
    public Deadline(String status, String description, String date) throws DateTimeParseException { // constructor when parsing tasks from hard disk
        super(TaskType.DEADLINE, status, description);
        this.date = TimeParser.parseDate(date);
        assert this.date != null : "date is null after parsing in constructor";
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
        return "[D]" + super.toString() + " (by: " + TimeParser.printDate(this.date) + ")";
    }
}
