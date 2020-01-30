import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Events: tasks that start at a specific time and ends at a specific time e.g., team project meeting on 2/10/2019 2-4pm
 */
public class Event extends Task {

    protected LocalDateTime at = null;

    /**
     * @param description the description of the event
     * @param at the time of the event
     * @param format the format to be used to parse this
     */
    public Event(String description, String at, DateTimeFormatter format) {
        super(description);
        this.at = LocalDateTime.parse(at, format);
    }

    /**
     * @return String to be output to the user
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(formatter) + ")";
    }

    /**
     * @return String for the file format
     */
    @Override
    public String fileString() {
        return "E | " + this.getStatusIcon() + " | " + description + " | " + at.format(formatter);
    }
}