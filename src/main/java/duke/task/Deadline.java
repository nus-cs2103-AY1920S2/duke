package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Creates a Deadline Task with date.
 */
public class Deadline extends Task {
    /**
     * Creates a Deadline Task with date.
     * @param msg Details of the Deadline Task
     * @param date LocalDate that the Deadline needs to be done by.
     */
    public Deadline(String msg, LocalDate date) {
        super(msg);
        super.type = "D";
        super.time = date;
    }

    @Override
    public String writeToFile() {
        return super.writeToFile() + " , " + super.time;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + super.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
