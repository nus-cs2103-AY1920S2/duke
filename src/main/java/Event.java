public class Event extends Task {
    protected String timing;

    /**
     * Constructor for the Event Class.
     *
     * @param description Event description.
     * @param timing      Timing at which the Event occurs.
     */
    public Event(String description, String timing) {
        super(description);
        this.timing = timing;
    }

    @Override
    public void snooze(String by) {
        this.timing = by;
    }

    @Override
    public String toString() {
        assert !(timing.equals("")) : "There is no timing provided for this event!";
        return "[E]" + super.toString() + " (at: " + timing + ")";
    }
}
