package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime timePeriod;

    public Event(String identifier, String timePeriod) throws DateTimeParseException{
        super(identifier);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy H:m");
        this.timePeriod = LocalDateTime.parse(timePeriod, formatter);
    }

    @Override
    public String toString() {
        return "Event: " + super.toString() + " (at: " + timePeriod + ")";
    }
}
