import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Event extends Task {
    private String eventTime;
    private static String messageFormat = "event description /at event_time";
    private static Pattern messageRegex = Pattern.compile("event (.+) /at (.+)");

    public static Event parseEvent(String msg) throws ParsingException {
        Matcher m = messageRegex.matcher(msg);
        
        if (m.matches()) {
            return new Event(m.group(1), m.group(2));
        } else {
            throw new ParsingException(messageFormat);
        }
    }

    public static String getMessageFormat() {
        return messageFormat;
    }

    public Event(String details, String eventTime) {
        super(details);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (at: %s)", eventTime);
    }
}