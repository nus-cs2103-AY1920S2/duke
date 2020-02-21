/**
 * Class representation of Event.
 */
public class Event extends Task {
    protected String time;

    /**
     * Event Constructor.
     * @param description
     * @param time
     */
    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    /**
     * Event's overriden toString method.
     * @return String representation of Event
     */
    public String toString() {
        if (tag.equals("")) {
            return ("[E] [" + this.getStatusIcon() + "] " + this.description + " (at: " + this.time + ")");
        } else {
            return ("[E] [" + this.getStatusIcon() + "] " + "[" + this.tag + "] " + this.description + " (at: " + this.time + ") ");
        }
    }
}
