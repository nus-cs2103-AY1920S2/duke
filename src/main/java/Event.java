/**
 * Represents an Event which inherits from Task and is stored/managed by Duke
 */
public class Event extends Task {
    /**
     * Stores the time the event is supposed to take place
     */
    protected String time;

    /**
     * Creates an Event object with given description and time
     * @param description
     * @param time
     */
    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    /**
     *  Gives a string representation of the Event by building upon parent's representation method
     * @return
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format("(at: %s", this.description);
    }
}
