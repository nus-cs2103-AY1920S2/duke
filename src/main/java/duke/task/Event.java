package duke.task;

import duke.exception.InvalidDateFormatException;
import duke.exception.InvalidTimeFormatException;
import duke.temporal.DateTimeParser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task.
 */
public class Event extends Task {
    /** Date that the event is happening. */
    private LocalDate atDate;
    /** Time of the day the event is happening. */
    private LocalTime atTime;

    /**
     * Constructs a new Event task with description and date.
     * Task is set to not done by default.
     *
     * @param description description of the task.
     * @param atDateTime date and time the event task is happening.
     * @throws InvalidDateFormatException if the date format is invalid.
     * @throws InvalidTimeFormatException if the time format is invalid.
     */
    public Event(String description, String atDateTime)
            throws InvalidDateFormatException, InvalidTimeFormatException {
        super(description, false);
        try {
            this.atDate = LocalDate.parse(DateTimeParser.getParsableDate(atDateTime));
        } catch (Exception e) {
            throw new InvalidDateFormatException(DateTimeParser.DATE_FORMAT_ERROR_MESSAGE);
        }
        try {
            this.atTime = LocalTime.parse(DateTimeParser.getParsableTime(atDateTime));
        } catch (Exception e) {
            throw new InvalidTimeFormatException(DateTimeParser.TIME_FORMAT_ERROR_MESSAGE);
        }
    }

    /**
     * Constructs a new Event task with description, date and done status.
     *
     * @param description description of the task.
     * @param isDone done status of the task.
     * @param atDateTime date and time the event task is happening.
     * @throws InvalidDateFormatException if the date format is invalid.
     * @throws InvalidTimeFormatException if the time format is invalid.
     */
    public Event(String description, boolean isDone, String atDateTime)
            throws InvalidDateFormatException, InvalidTimeFormatException {
        super(description, isDone);
        try {
            this.atDate = LocalDate.parse(DateTimeParser.getParsableDate(atDateTime));
        } catch (Exception e) {
            throw new InvalidDateFormatException(DateTimeParser.DATE_FORMAT_ERROR_MESSAGE);
        }
        try {
            this.atTime = LocalTime.parse(DateTimeParser.getParsableTime(atDateTime));
        } catch (Exception e) {
            throw new InvalidTimeFormatException(DateTimeParser.TIME_FORMAT_ERROR_MESSAGE);
        }
    }

    /**
     * Gets the date that the event task is happening.
     *
     * @return the date that the event task is happening.
     */
    public LocalDate getAtDate() {
        return atDate;
    }

    /**
     * Gets the time that the event task is happening.
     *
     * @return the time that the event task is happening.
     */
    public LocalTime getAtTime() {
        return atTime;
    }

    /**
     * Gets the string representation of the event task used for saving.
     *
     * @return the string representation of the event task to be used for saving.
     */
    @Override
    public String getSaveRepresentation() {
        return "E|||" + getIsDone() + "|||" + getDescription() + "|||"
                + atDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy "))
                + atTime.format(DateTimeFormatter.ofPattern("kmm")) + "\n";
    }

    /**
     * Gets the string representation of the event task.
     *
     * @return the string representation of the event task.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s (at: %s, %s)", "E", (getIsDone() ? "Y" : "N"), getDescription(),
                atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                atTime.format(DateTimeFormatter.ofPattern("h:mma")));
    }
}