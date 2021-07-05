/**
 * Event class that is a type of Task.
 */
public class Event extends Task {
    String timeline;

    /**
     * Creates a Event Task object.
     *
     * @param description A string representation of the task description.
     * @param timeline A string representation of the time for the task.
     */
    public Event(String description, String timeline) {
        super(description);
        this.timeline = timeline;
    }

    /**
     * Gets the task description, time and status whether is it done.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.timeline + ")";
    }
}