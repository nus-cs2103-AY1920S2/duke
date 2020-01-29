/**
 * This is a subclass of task which simulates tasks with event time.
 */
public class Event extends Task{

    /** Event time of this task */
    protected String at;

    /**
     * Class constructor inherits form Task constructor and add event time.
     *
     * @param description Description of this Event.
     * @param at Event time.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Override String representation of event.
     *
     * @return Type E and its description and event time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String getData() {
        return "E" + super.getData() + "|" + this.at;
    }
}
