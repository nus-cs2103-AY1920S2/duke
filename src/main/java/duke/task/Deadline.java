package duke.task;

import duke.DukeException;

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
     * @param description The description of the deadline.
     * @param by The due date and time of the deadline in yyyy-mm-dd hh:mm format.
     * @throws DukeException If the date or time is in the incorrect format.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            String[] dateTimeArr = by.split(" ");
            this.date = LocalDate.parse(dateTimeArr[0]);
            this.time = LocalTime.parse(dateTimeArr[1]);
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Incorrect date or time format. Format required: yyyy-mm-dd hh:mm");
        }
    }

    /**
     * Constructs a Deadline with the specified description, due date and time and status.
     * @param description The description of the deadline.
     * @param by The due date and time of the deadline in yyyy-mm-dd hh:mm format.
     * @param isDone Whether the deadline is done.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        String[] dateTimeArr = by.split(" ");
        this.date = LocalDate.parse(dateTimeArr[0]);
        this.time = LocalTime.parse(dateTimeArr[1]);
    }

    /**
     * Returns the date of the deadline.
     * @return The date of the deadline.
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Returns a string representation of the deadline for saving to the disk.
     * @return String representation of the deadline for saving to the disk.
     */
    @Override
    public String formatToSave() {
        return "D" + super.formatToSave() + " | " + date + " " + time;
    }

    /**
     * Returns a string representation of the deadline for printing.
     * @return String representation of the deadline for printing.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " " + time + ")";
    }
}
