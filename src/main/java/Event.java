import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Creates an Event Task with date.
 */

public class Event extends Task {

    /**
     * Creates an Event Task with LocalDate.
     * @param msg Details of the Event Task.
     * @param date Date that the Event will be done at.
     */
    public Event(String msg, LocalDate date) {
        super(msg);
        super.type = "E";
        super.time = date;
    }

    @Override
    public String writeToFile() {
        return "E , " + super.status + " ," + super.msg + " , " + super.time;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + super.status + "]" + super.msg + " (by: "
                + super.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
