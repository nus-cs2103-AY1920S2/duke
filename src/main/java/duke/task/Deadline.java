package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Creates a Deadline Task with date.
 */
public class Deadline extends DateTask {
    /**
     * Creates a Deadline Task with date.
     * @param msg Details of the Deadline Task
     * @param date LocalDate that the Deadline needs to be done by.
     */
    public Deadline(String msg, LocalDate date) {
        super(msg, date, "by");
        super.type = "D";
    }

}
