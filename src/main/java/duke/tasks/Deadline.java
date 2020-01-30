package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadlines: tasks that need to be done before a specific date/time
 * e.g., submit report by 11/10/2019 5pm.
 */
public class Deadline extends Task {

    private LocalDateTime by;

    /**
     * creates a new Deadline.
     * @param description the description of the deadline
     * @param by the time of the deadline
     * @param format the format to be used to parse this
     */
    public Deadline(String description, String by, DateTimeFormatter format) {
        super(description);
        this.by = LocalDateTime.parse(by, format);
    }

    /**
     * returns output string.
     * @return String to be output to the user
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(FORMATTER) + ")";
    }

    /**
     * returns file data string.
     * @return String for the file format
     */
    @Override
    public String fileString() {
        return "D | " + this.getStatusIcon() + " | " + getDescription() + " | "
                + by.format(FORMATTER);
    }
}
