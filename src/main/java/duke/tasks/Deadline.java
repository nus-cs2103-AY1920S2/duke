package duke.tasks;

import static duke.commands.Parser.FORMATTER;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadlines: tasks that need to be done before a specific date/time
 * e.g., submit report by 11/10/2019 5pm.
 */
public class Deadline extends Task {

    /**
     * the time for this deadline.
     */
    private LocalDateTime by;

    /**
     * creates a new Deadline.
     *
     * @param description the description of the deadline
     * @param time the time of the deadline
     * @param format the format to be used to parse this
     */
    public Deadline(String description, String time, DateTimeFormatter format)
            throws DateTimeParseException {
        super(description);
        assert description != null : "No description for this deadline";
        this.by = LocalDateTime.parse(time, format);
        assert this.by != null : "No timing for this deadline";
    }

    /**
     * Updates the timing.
     *
     * @param time the new timing
     * @param format the format to be used to parse this
     */
    public void update(String time, DateTimeFormatter format) {
        this.by = LocalDateTime.parse(time, format);
    }

    /**
     * returns output string.
     *
     * @return String to be output to the user
     */
    @Override
    public String toString() {
        assert this.getDescription() != null
                : "No description for this deadline";
        assert this.by != null : "No timing for this deadline";
        return "[D]" + super.toString() + " (by: " + by.format(FORMATTER) + ")";
    }

    /**
     * returns file data string.
     *
     * @return String for the file format
     */
    @Override
    public String fileString() {
        assert this.getDescription() != null
                : "No description for this deadline";
        assert this.by != null : "No timing for this deadline";
        return "D | " + this.getFileIcon() + " | " + getDescription() + " | "
                + by.format(FORMATTER);
    }
}
