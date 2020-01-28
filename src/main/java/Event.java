public class Event extends Task {
    protected String timing;

    /**
     * Constructor for the Event Class.
     *
     * @param description Event description.
     * @param timing Timing at which the Event occurs.
     */
    public Event(String description, String timing) {
        super(description);
        this.timing = timing;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + timing + ")";
    }
}
