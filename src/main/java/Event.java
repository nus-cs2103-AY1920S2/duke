import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents an event with a description, start time and end time.
 */
public class Event extends Task {
    Date startTime;
    Date endTime;

    /**
     * Constructor for Event that takes in a description of the event and
     * a time range describing the start and end time.
     *
     * @param description the description of the event
     * @param timeRange String containing the start and end time, separated by " to ",
     *                  in the "yyyy-mm-dd HH:mm" format
     */
    public Event(String description, String timeRange) {
        super(description);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm");
        String times[] = timeRange.split(" to ");

        startTime = format.parse(times[0], new ParsePosition(0));
        endTime = format.parse(times[1], new ParsePosition(0));
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("MMM d yyyy HH:mm");
        return "[E]" + super.toString() + " (at: " + format.format(startTime) + " to " + format.format(endTime) + ")";
    }
}
