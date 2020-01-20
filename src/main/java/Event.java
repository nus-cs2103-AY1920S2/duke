public class Event extends Task {
    protected String atDateAndTime;

    public Event(String description, String atDateAndTime) {
        super(description);
        this.atDateAndTime = atDateAndTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + atDateAndTime + ")";
    }
}
