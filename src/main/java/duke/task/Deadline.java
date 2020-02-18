package duke.task;

import duke.exception.DukeException;
import duke.exception.DukeInvalidDateTimeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline.
 */
public class Deadline extends Task {

    protected LocalDate byDate;
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
            String[] dateAndTime = by.split(" ");
            this.byDate = LocalDate.parse(dateAndTime[0]);
            //time is optional
            if (dateAndTime.length == 2) {
                this.byTime = LocalTime.parse(dateAndTime[1]);
            } else {
                this.byTime = null;
            }
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateTimeException();
        }
    }

    /**
     * Returns a string representation of the deadline that will be saved in storage.
     *
     * @return The string representation of the deadline that will be saved in storage.
     */
    @Override
    public String toSaveName() {
        String time = (byTime != null ? " " + byTime : "");
        return "D" + super.toSaveName() + " | " + this.byDate + time + "\n";
    }

    /**
     * Returns a string representation of the deadline.
     *
     * @return The string representation of the deadline.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[MMM d yyyy][h:mma]");
        String time = (byTime != null ? " " + byTime.format(formatter) : "");
        return "[D]" + super.toString() + " (by: " + byDate.format(formatter) + time + ")";
    }
}
