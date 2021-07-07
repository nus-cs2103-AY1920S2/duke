package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class representing a Deadline.
 */
public class Deadline extends Task {

    /**
     * Creates a Deadline object.
     *
     * @param description String containing what to do.
     * @param time LocalDateTime object representing time that task must be completed by.
     */
    public Deadline(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }

    /**
     * Returns a String representation of the Deadline object.
     *
     * @return String representation of the deadline to print.
     */
    @Override
    public String toString() {
        String timeStr = this.time.format(DateTimeFormatter.ofPattern("HH:mm, MMM d yyyy"));
        return String.format("[D]%s (by: %s)", super.toString(), timeStr);
    }

    /**
     * Returns a String representation of the Deadline object to save.
     *
     * @return String representation of the deadline to save.
     */
    @Override
    public String toSaveString() {
        String timeStr = this.time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        return String.format(
                "%s || deadline || %s || %s", super.toSaveString(), this.description, timeStr);
    }
}
