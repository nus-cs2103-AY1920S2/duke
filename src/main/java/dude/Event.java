package dude;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Event extends Task {
    public static final String USAGE = "event description /at event_time";
    private String eventTime;
    private static Pattern messageRegex = Pattern.compile("event\\s+(\\S+.*)\\s+/at\\s+(\\S+.*)");

    public static Event parseEvent(String msg) throws ParsingException {
        Matcher m = messageRegex.matcher(msg);
        
        if (m.matches()) {
            return new Event(m.group(1).strip(), m.group(2).strip(), false);
        } else {
            throw new ParsingException(USAGE);
        }
    }

    public Event(String details, String eventTime, boolean isDone) {
        super(details, isDone);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (at: %s)", eventTime);
    }

}