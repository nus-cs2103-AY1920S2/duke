package task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * This is the main class for the Event object. It is represented by a
 * description of the activity and a Date object that refers to the event start time.
 */
public class Event extends Task {

    protected String at;
    protected LocalDate date;
    protected LocalTime time;
    protected LocalDateTime dateTime;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * This method formats the Event object based on its representation into a format suitable for
     * writing to a file.
     *
     * @return A String object that can be easily written and retrieved from the data file.
     */
    public String format() {
        return "E" + " | " + (this.isDone?"1":"0") + " | " + description + " | " + date;
    }

    public Event(String description, LocalDate date) {
        super(description);
//        this.dateTime = dateTime;
        this.date = date;
    }

    /**
     * A specialised toString() method based on implementations of the object.
     *
     * @return A specialised String representation of the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
