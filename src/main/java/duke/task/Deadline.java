package duke.task;

import duke.exception.InvalidDateFormatException;
import duke.exception.InvalidTimeFormatException;
import duke.temporal.DateTimeParser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {
    /** Date the deadline task is due. */
    private LocalDate byDate;
    /** Time of the day the deadline task is due. */
    private LocalTime byTime;

    /**
     * Constructs a new Deadline task with description and date.
     * Task is set to not done by default.
     *
     * @param description description of the task.
     * @param byDateTime date and time the deadline task is due.
     * @throws InvalidDateFormatException if the date format is invalid.
     * @throws InvalidTimeFormatException if the time format is invalid.
     */
    public Deadline(String description, String byDateTime)
            throws InvalidDateFormatException, InvalidTimeFormatException  {
        super(description, false);
        try {
            this.byDate = LocalDate.parse(DateTimeParser.getParsableDate(byDateTime));
        } catch (Exception e) {
            throw new InvalidDateFormatException(DateTimeParser.DATE_FORMAT_ERROR_MESSAGE);
        }
        try {
            this.byTime = LocalTime.parse(DateTimeParser.getParsableTime(byDateTime));
        } catch (Exception e) {
            throw new InvalidTimeFormatException(DateTimeParser.TIME_FORMAT_ERROR_MESSAGE);
        }
    }

    /**
     * Constructs a new Deadline task with description, date and done status.
     *
     * @param description description of the task.
     * @param isDone done status of the task.
     * @param byDateTime date and time the deadline task is due.
     * @throws InvalidDateFormatException if the date format is invalid.
     * @throws InvalidTimeFormatException if the time format is invalid.
     */
    public Deadline(String description, boolean isDone, String byDateTime)
            throws InvalidDateFormatException, InvalidTimeFormatException {
        super(description, isDone);
        try {
            this.byDate = LocalDate.parse(DateTimeParser.getParsableDate(byDateTime));
        } catch (Exception e) {
            throw new InvalidDateFormatException(DateTimeParser.DATE_FORMAT_ERROR_MESSAGE);
        }
        try {
            this.byTime = LocalTime.parse(DateTimeParser.getParsableTime(byDateTime));
        } catch (Exception e) {
            throw new InvalidTimeFormatException(DateTimeParser.TIME_FORMAT_ERROR_MESSAGE);
        }
    }

    /**
     * Gets the due date of the deadline task.
     *
     * @return the due date of the deadline task.
     */
    public LocalDate getByDate() {
        return byDate;
    }

    /**
     * Gets the time the deadline task is due.
     *
     * @return the time the deadline task is due.
     */
    public LocalTime getByTime() {
        return byTime;
    }

    /**
     * Gets the string representation of the deadline task used for saving.
     *
     * @return the string representation of the deadline task to be used for saving.
     */
    @Override
    public String getSaveRepresentation() {
        return "D|||" + getIsDone() + "|||" + getDescription() + "|||"
                + byDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy "))
                + byTime.format(DateTimeFormatter.ofPattern("kmm")) + "\n";
    }

    /**
     * Gets the string representation of the deadline task.
     *
     * @return the string representation of the deadline task.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s, %s)", "D", (getIsDone() ? "Y" : "N"), getDescription(),
                byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                byTime.format(DateTimeFormatter.ofPattern("h:mma")));
    }
}