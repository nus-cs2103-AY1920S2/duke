package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.utilities.TimeParser;

public class Deadline extends Task implements TimeParser {
    protected LocalDate deadline;

    /**
     * Another constructor for creating Deadline.
     * This constructor is called in the Parser class when parsing file to string.
     * Sets isDone to true or false according to status number (0 or 1)
     *
     * @param status      0 - isDone is false, 1 - isDone is true
     * @param description description of deadline
     * @param deadline    String representation of the deadline
     * @throws DateTimeParseException
     */
    public Deadline(String status, String description, String deadline) throws DateTimeParseException { // constructor when parsing tasks from hard disk
        super(TaskType.DEADLINE, status, description);
        this.deadline = TimeParser.parseDate(deadline);
        assert this.deadline != null : "date is null after parsing in constructor";
    }


    public LocalDate getTaskTime() {
        return this.deadline;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + TimeParser.printDate(this.deadline) + ")";
    }
}
