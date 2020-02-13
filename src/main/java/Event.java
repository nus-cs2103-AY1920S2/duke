/**
 * Represents a event task. An <code>Event</code> object corresponds to an
 * event task represented by task description and date-time information.
 */

public class Event extends Task {
    protected String dayTime;

    public Event(String description, String dayTime) {
        super(description);
        this.dayTime = Parser.parseTime(dayTime);
    }

    /**
     * Returns event day (and time).
     *
     * @return Day and time.
     */
    public String getDayTime() {
        return dayTime;
    }
}
