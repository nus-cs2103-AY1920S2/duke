package dude;

import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.time.LocalDate;

public class Event extends Task {
    public static final String USAGE = "event description /at yyyy-mm-dd";
    private LocalDate eventTime;
    private static Pattern messageRegex =
            Pattern.compile("event\\s+(\\S+.*)\\s+/at\\s+(\\d{4}-\\d{2}-\\d{2})");

    public static Event parseEvent(String msg) throws ParsingException {
        Matcher m = messageRegex.matcher(msg);
        
        if (m.matches()) {
            return new Event(m.group(1), LocalDate.parse(m.group(2)));
        } else {
            throw new ParsingException(USAGE);
        }
    }

    public Event(String details, LocalDate eventTime) {
        super(details);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (by: %s)", super.toString(),
                eventTime.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }
}