package dude;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    public static final String USAGE = "event description /from yyyy-mm-dd /to yyyy-mm-dd";
    private LocalDate eventStart;
    private LocalDate eventEnd;
    private static Pattern messageRegex =
            Pattern.compile("event\\s+(\\S+.*)\\s+"
                    + "/from\\s+(\\d{4}-\\d{2}-\\d{2})\\s+"
                    + "/to\\s+(\\d{4}-\\d{2}-\\d{2})");

    public static Event parseEvent(String msg) throws ParsingException {
        Matcher m = messageRegex.matcher(msg);
        
        if (m.matches()) {
            try {
                LocalDate evStart = LocalDate.parse(m.group(2));
                LocalDate evEnd = LocalDate.parse(m.group(3));
                return new Event(m.group(1).strip(), evStart, evEnd, false);
            } catch (DateTimeParseException e) {
                throw new ParsingException(USAGE);
            }
        } else {
            throw new ParsingException(USAGE);
        }
    }

    public Event(String details, LocalDate eventStart, LocalDate eventEnd, boolean isDone) {
        super(details, isDone);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    @Override
    boolean occursOn(LocalDate date) {
        return (date.isAfter(this.eventStart) || date.isEqual(this.eventStart))
                && (date.isBefore(this.eventEnd) || date.isEqual(this.eventEnd));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (%s to %s)", super.toString(),
                eventStart.format(DateTimeFormatter.ofPattern("d MMM yyyy")),
                eventEnd.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }

}