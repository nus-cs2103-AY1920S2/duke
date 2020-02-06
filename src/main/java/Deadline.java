/**
 * Represents a deadline task. A <code>Deadline</code> object corresponds to a
 * deadline task represented by task description and date-time information.
 */

public class Deadline extends Task {
    protected String dayTime;

    public Deadline(String description, String dayTime) {
        super(description);
        this.dayTime = Parser.parseTime(dayTime);
    }

    /**
     * Returns deadline day (and time).
     *
     * @return Day and time.
     */
    public String getDayTime() {
        return dayTime;
    }
}
