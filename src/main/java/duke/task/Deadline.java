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

    protected LocalDate by;
    protected LocalTime byTime;

    /**
     * Constructs a Deadline with the specified description and date and/or time.
     *
     * @param description The specified description of the deadline.
     * @param by The specified date and/or time of the deadline.
     * @throws DukeException If the date or time are wrongly formatted.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            String[] temp = by.split(" ");
            this.by = LocalDate.parse(temp[0]);
            if (temp.length == 2) {
                this.byTime = LocalTime.parse(temp[1]);
            } else {
                this.byTime = null;
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!!! Format is yyyy-mm-dd HH:mm. Time is optional.");
        }
    }

    /**
     * Returns a string representation of the deadline that will be saved in storage.
     *
     * @return The string representation of the deadline that will be saved in storage.
     */
    @Override
    public String toSaveName() {
        return "D" + super.toSaveName() + " | " + this.by
                + (byTime != null ? " " + byTime : "") + "\n";
    }

    /**
     * Returns a string representation of the deadline.
     *
     * @return The string representation of the deadline.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[MMM d yyyy][h:mma]");
        return "[D]" + super.toString() + " (by: " +  by.format(formatter)
                + (byTime != null ? " " + byTime.format(formatter) : "") + ")";
    }
}
