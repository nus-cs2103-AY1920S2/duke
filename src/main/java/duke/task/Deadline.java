package duke.task;

import duke.exception.DukeException;
import duke.exception.InvalidDateTimeFormatException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline.
 */
public class Deadline extends Task {
    protected LocalDate date;
    protected LocalTime time;

    /**
     * Constructs a Deadline with the specified description and due date and time.
     *
     * @param description The description of the deadline.
     * @param by The due date and time of the deadline in yyyy-mm-dd hh:mm format.
     * @throws DukeException If the date or time is in the incorrect format.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            String[] dateTimeArray = by.split(" ", 2);
            this.date = LocalDate.parse(dateTimeArray[0].trim());
            this.time = LocalTime.parse(dateTimeArray[1].trim());
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidDateTimeFormatException("yyyy-mm-dd hh:mm");
        }
    }

    /**
     * Constructs a Deadline with the specified description, due date and time and status.
     *
     * @param description The description of the deadline.
     * @param by The due date and time of the deadline in yyyy-mm-dd hh:mm format.
     * @param isDone Whether the deadline is done.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        String[] dateTimeArray = by.split(" ");
        this.date = LocalDate.parse(dateTimeArray[0]);
        this.time = LocalTime.parse(dateTimeArray[1]);
    }

    /**
     * Returns the date of the deadline.
     *
     * @return The date of the deadline.
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Sets the date of the deadline to the specified date.
     * @param date The date of the deadline.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Sets the time of the deadline to the specified time.
     * @param time The time of the deadline.
     */
    public void setTime(LocalTime time) {
        this.time = time;
    }

    /**
     * Returns a string representation of the deadline for saving to the disk.
     *
     * @return String representation of the deadline for saving to the disk.
     */
    @Override
    public String formatToSave() {
        return "D" + super.formatToSave() + " | " + date + " " + time;
    }

    /**
     * Returns a string representation of the deadline for printing.
     *
     * @return String representation of the deadline for printing.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " " + time + ")";
    }
}
