package duke.task;

import duke.exceptions.InvalidArgumentException;
import duke.exceptions.InvalidDateTimeFormatException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event class that extends Task, by adding a new parameter called "on", to dictate when the deadline is
 */
public class Deadline extends Task {

    /**
     * Variable to store when the Deadline is
     */
    public LocalDateTime by;

    /**
     * Constructor for Deadline object, specifying the description and datetime at which the deadline is
     * @param description Description of the Deadline
     * @param by String representation of Date & Time at which the deadline is
     */
    public Deadline(String description, String by) throws InvalidDateTimeFormatException {
        super(description);
        try {
            DateTimeFormatter inputDtf = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime outputDt = LocalDateTime.parse(by, inputDtf);
            DateTimeFormatter outputDtF = DateTimeFormatter.ofPattern("d MMMM yyyy, h:mm a");
            this.by = LocalDateTime.parse(outputDt.format(outputDtF),
                DateTimeFormatter.ofPattern("d MMMM yyyy, h:mm a"));
        } catch (DateTimeException ex) {
            throw new InvalidDateTimeFormatException();
        }
    }

    /**
     * Returns a String representation of the Task object
     * @return a String representation of the Task object
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
            + by.format(DateTimeFormatter.ofPattern("d MMMM yyyy, h:mm a")) + ")";
    }


    public boolean snooze(LocalDateTime datetime) {
        this.by = datetime;
        return true;
    }
}