/** Represents a Task which will happen at a certain date and time. */
public class Event extends Task {

    protected TaskDate td;

    /**
     * Constructor for Event object.
     *
     * @param desc Task description
     * @param td TaskDate event date and time
     */
    public Event(String desc, TaskDate td) {
        super(desc);
        this.td = td;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + td + ")";
    }
}