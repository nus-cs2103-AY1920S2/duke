public class Event extends Task {

    // TODO: Implement start/end time
    protected String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    protected String getTypeIcon() {
        return "[E]";
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + time + ")";
    }
}
