public class Event extends Task {

    protected String by;

    public Event(String event, String by) {
        super(event);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + by + ")";
    }
}