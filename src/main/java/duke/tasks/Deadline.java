package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static duke.commands.Parser.FORMATTER;

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
     * @param by the time of the deadline
     * @param format the format to be used to parse this
     */
    public Deadline(String description, String by, DateTimeFormatter format) {
        super(description);
        assert description != null : "No description for this deadline";
        this.by = LocalDateTime.parse(by, format);
        assert by != null : "No timing for this deadline";
    }

    /**
     * Updates the timing.
     *
     * @param by the new timing
     * @param format the format to be used to parse this
     */
    public void update(String by, DateTimeFormatter format) {
        this.by = LocalDateTime.parse(by, format);
    }

    /**
     * returns output string.
     *
     * @return String to be output to the user
     */
    @Override
    public String toString() {
        assert this.getDescription() != null : "No description for this deadline";
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
        assert this.getDescription() != null : "No description for this deadline";
        assert this.by != null : "No timing for this deadline";
        return "D | " + this.getStatusIcon() + " | " + getDescription() + " | "
                + by.format(FORMATTER);
    }
}
