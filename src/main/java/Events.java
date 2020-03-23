/**
 * Represents an Event object.
 */
public class Events extends Task {
    protected String at;

    /**
     * Event constructor.
     * @param description description of event
     * @param isDone checks if event is done
     * @param at more description of event
     */
    public Events(String description, Boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
        System.out.println(this);
    }

    public String getVenue() {
        return this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (At: " + at + ")";
    }
}
