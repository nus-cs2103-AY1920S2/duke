package dude.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate eventStart;
    private LocalDate eventEnd;

    public Event(String details, LocalDate eventStart, LocalDate eventEnd, boolean isDone) {
        super(details, isDone);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    @Override
    public boolean occursOn(LocalDate date) {
        return (date.isAfter(this.eventStart) || date.isEqual(this.eventStart))
                && (date.isBefore(this.eventEnd) || date.isEqual(this.eventEnd));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (%s to %s)", super.toString(),
                eventStart.format(DateTimeFormatter.ofPattern("d MMM yyyy")),
                eventEnd.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }

    @Override
    public String storeFormat() {
        return String.format("E|%s|%s|%s|%s", getStatusIcon(), getDetails(), this.eventStart, this.eventEnd);
    }
}