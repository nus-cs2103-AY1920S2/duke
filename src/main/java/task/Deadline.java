package task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * This is the main class for the Deadline object. It is represented by a
 * description of the activity and a Date object that refers to the completion deadline.
 */
public class Deadline extends Task {

    protected String by;
    protected LocalDate date;
    protected LocalTime time;
    protected LocalDateTime dateTime;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * This method formats the Deadline object based on its representation into a format suitable for
     * writing to a file.
     *
     * @return A String object that can be easily written and retrieved from the data file.
     */
    public String toTxtFormat() {
        return "D" + " | " + (this.isDone ? "1" : "0") + " | " + description + " | " + date;
    }

    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * A specialised toString() method based on implementations of the object.
     *
     * @return A specialised String representation of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}