package dude.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate eventStart;
    private LocalDate eventEnd;

    /**
     * Initializes a Event task with given details, start date, end date and completion status indicated by isDone.
     *
     * @param details description of the event.
     * @param eventStart Date which the event starts.
     * @param eventEnd Date which the event ends.
     * @param isDone completion status of the event.
     */
    public Event(String details, LocalDate eventStart, LocalDate eventEnd, boolean isDone) {
        super(details, isDone);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    /**
     * Indicates if this Task occurs on the given date.
     *
     * @param date date of interest as to whether the Task occurs on that date.
     * @return true if date is within the range from eventStart to eventEnd, false otherwise.
     */
    @Override
    public boolean occursOn(LocalDate date) {
        return (date.isAfter(this.eventStart) || date.isEqual(this.eventStart))
                && (date.isBefore(this.eventEnd) || date.isEqual(this.eventEnd));
    }

    /**
     * Returns a string representation of the Task, meant to be understood by users.
     *
     * @return String displaying Task type, isDone status, Task description, eventStart and eventEnd,
     *         in the format: [E][isDone] description (eventStart to eventEnd).
     */
    @Override
    public String toString() {
        return String.format("[E]%s (%s to %s)", super.toString(),
                eventStart.format(DateTimeFormatter.ofPattern("d MMM yyyy")),
                eventEnd.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }

    /**
     * Returns a string representation of the Task, meant to be written to a plain text file and easily parsed.
     *
     * @return formatted string in the format: E|isDone|description|eventStart|eventEnd.
     */
    @Override
    public String storeFormat() {
        return String.format("E|%s|%s|%s|%s", getStatusIcon(), getDetails(), this.eventStart, this.eventEnd);
    }
}