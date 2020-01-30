package com.duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline Task.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Creates a Deadline Task with the given description and deadline time.
     *
     * @param description The description of the deadline event.
     * @param by          The time by which the deadline event terminates.
     * @throws DateTimeParseException When the date input is invalid.
     */
    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        this.by = Task.generateTime(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String generateWriteFormat() {
        int k = this.isDone ? 1 : 0;
        return "D|" + k + "|" + description + "|" + by;
    }
}