package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Creates an Event Task with date.
 */

public class Event extends DateTask {

    /**
     * Creates an Event Task with LocalDate.
     * @param msg Details of the Event Task.
     * @param date Date that the Event will be done at.
     */
    public Event(String msg, LocalDate date) {
        super(msg, date, "at");
        super.type = "E";
    }

}
