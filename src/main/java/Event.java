/**
 * Event Class.
 * Event Class is a subclass of Task Class. It stores the description of this instance
 * as well as the date and time of the particular event. It is not a Deadline task
 * because its main purpose is to indicate the date of an event.
 *
 * @author Amos Cheong
 */
public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String getDesc() {
        return super.description;
    }

    public String getDate() {
        return at;
    }

    public String getType() {
        return "[E]";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}