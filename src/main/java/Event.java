/**
 * Event Class.
 * Event Class is a subclass of Task Class. It stores the description of this instance
 * as well as the date and time of the particular event.
 *
 * @author Amos Cheong
 */
public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }
    /**
     * Get the description of this Event object.
     * @return String description of this Event object.
     */
    public String getDesc() {
        return super.description;
    }

    /**
     * Get the date and time of this Event object.
     * @return String date and time of this Event object.
     */
    public String getDate() {
        return at;
    }

    /**
     * Get the type of this object.
     * Used when outputting to user.
     * @return String The object type.
     */
    public String getType() {
        return "[E]";
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}