import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    Date startTime;
    Date endTime;

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
