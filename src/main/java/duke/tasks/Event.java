package duke.tasks;

import static duke.commands.Parser.FORMATTER;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Events: tasks that start at a specific time and ends at a specific time
 * e.g., team project meeting on 2/10/2019 2-4pm.
 */
public class Event extends Task {

    /**
     * the time for this event.
     */
    private LocalDateTime at;

    /**
     * creates a new Event.
     *
     * @param description the description of the event
     * @param time the time of the event
     * @param format the format to be used to parse this
     */
    public Event(String description, String time, DateTimeFormatter format)
            throws DateTimeParseException {
        super(description);
        assert description != null : "No description for this event";
        this.at = LocalDateTime.parse(time, format);
        assert this.at != null : "No timing for this event";
    }

    /**
     * Updates the timing.
     *
     * @param time the new timing
     * @param format the format to be used to parse this
     */
    public void update(String time, DateTimeFormatter format) {
        this.at = LocalDateTime.parse(time, format);
    }

    /**
     * returns output string.
     *
     * @return String to be output to the user
     */
    @Override
    public String toString() {
        assert this.getDescription() != null : "No description for this event";
        assert this.at != null : "No timing for this event";
        return "[E]" + super.toString() + " (at: " + at.format(FORMATTER) + ")";
    }

    /**
     * returns file data string.
     *
     * @return String for the file format
     */
    @Override
    public String fileString() {
        assert this.getDescription() != null : "No description for this event";
        assert this.at != null : "No timing for this event";
        return "E | " + this.getFileIcon() + " | " + getDescription() + " | "
                + at.format(FORMATTER);
    }
}
