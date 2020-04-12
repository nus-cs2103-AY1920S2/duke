/**
 * Event is a Task that is described by its <code>String</code> description and
 * a <code>boolean</code> isDone to indicate whether an Event is completed.
 * <code>at</code> describes the time of the Event.
 */
public class Event extends Task {
    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
