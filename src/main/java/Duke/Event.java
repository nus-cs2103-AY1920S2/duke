package duke;

/**
 * Event class that inherits from Task class.
 */
public class Event extends Task {
    protected String timefin;

    /**
     * Constructor for Event.
     * @param description Takes in a description
     * @param timefin Takes in a String of finishing time
     */
    public Event(String description, String timefin) {
        super(description);
        this.timefin = timefin;
    }

    /**
     * To string method for Event.
     * @return string for toString method
     */
    @Override
    public String toString() {
        return "[E]" + "[" + super.getStatusIcon() + "] " + super.toString() + " (at: " + timefin + ")";
    }

}
