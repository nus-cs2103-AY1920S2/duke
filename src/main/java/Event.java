public class Event extends Task {
    protected String eventTime;

    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        String formattedEventTime = " (at: " + this.eventTime + ")";
        return "[E]" + super.toString() + formattedEventTime;
    }
}
