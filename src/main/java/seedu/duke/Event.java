package seedu.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate eventTime;

    public Event(String description, LocalDate eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        String formattedEventTime = " (at: " + this.eventTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        return "[E]" + super.toString() + formattedEventTime;
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public LocalDate getTime() {
        return eventTime;
    }
}
