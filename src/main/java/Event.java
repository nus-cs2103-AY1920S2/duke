import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task
 */
public class Event extends Task {

    /** On what date the event is held on*/
    protected LocalDate date;
    /** What time the event starts*/
    protected LocalTime start;
    /** What time the event ends*/
    protected LocalTime end;

    /**
     * Creates an Event Task object
     *
     * @param description What the event is
     * @param date What day the event is on
     * @param start When time the event starts
     * @param end What time the event ends
     */
    public Event (String description, LocalDate date, LocalTime start, LocalTime end) {
        super (description);
        this.date = date;
        this.start = start;
        this.end = end;
    }

    /**
     * Formats the Event object so it can be saved on the hard disk
     *
     * @return a formatted string that can be saved onto hard disk
     */
    @Override
    public String saveFile() {
        if (this.status.equals ("Done")) {
            return  "E|1||" + this.description + "|||" + this.date + " "
                    + this.start + "-" + this.end;
        } else {
            return  "E|0||" + this.description + "|||" + this.date + " "
                    + this.start + "-" + this.end;
        }
    }

    /**
     * Converts Event object into string format to describe the event
     *
     * @return A string that describes the event,
     * what type of event, and schedule of event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: "
                + this.date.format (DateTimeFormatter.ofPattern ("MMM d yyyy, "))
                + this.start.format (DateTimeFormatter.ofPattern ("hh:mm a")) + " - "
                + this.end.format (DateTimeFormatter.ofPattern ("hh:mm a")) + ")";
    }
}

