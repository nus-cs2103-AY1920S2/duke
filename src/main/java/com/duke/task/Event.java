package com.duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event Task.
 */
public class Event extends Task {
    protected LocalDate at;

    /**
     * Creates a event task with the given description and date information.
     *
     * @param description description of the event.
     * @param a           the date information of the event.
     * @throws DateTimeParseException when the date information is in invalid format.
     */
    public Event(String description, String a) throws DateTimeParseException {
        super(description);
        this.at = Task.generateTime(a);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String generateWriteFormat() {
        int k = this.isDone ? 1 : 0;
        return "E|" + k + "|" + description + "|" + at;
    }
}
