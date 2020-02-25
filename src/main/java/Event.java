import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InvalidPropertiesFormatException;

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
    public Event(String description, String timeRange) throws InvalidPropertiesFormatException {
        super(description);

        type = "Event";
        isSnoozeable = true;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String[] times = timeRange.split(" to ");

        // check if two sets of time is present
        assert times.length == 2;

        startTime = format.parse(times[0], new ParsePosition(0));
        endTime = format.parse(times[1], new ParsePosition(0));

        if (this.startTime == null || this.endTime == null) {
            // invalid format
            throw new InvalidPropertiesFormatException("Wrong date format");
        }
    }

    @Override
    public String getType() {
        return type;
    }

    /**
     * Returns a snoozed Event.
     *
     * @param time the time after snooze
     * @return
     */
    @Override
    public Task snooze(String time) throws InvalidPropertiesFormatException {
        return new Event(description, time);
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("MMM d yyyy HH:mm");
        return "[E]" + super.toString() + " (at: " + format.format(startTime) + " to " + format.format(endTime) + ")";
    }
}
