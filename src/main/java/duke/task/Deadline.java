package duke.task;

import duke.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDate by;
    protected LocalTime byTime;

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

    @Override
    public String toSaveName() {
        return "D" + super.toSaveName() + " | " + this.by
                + (byTime != null ? " " + byTime : "") + "\n";
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[MMM d yyyy][h:mma]");
        return "[D]" + super.toString() + " (by: " +  by.format(formatter)
                + (byTime != null ? " " + byTime.format(formatter) : "") + ")";
    }
}
