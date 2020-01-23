public class Event extends Task {
    String timeline;

    Event(String description, String timeline) {
        super(description);
        this.timeline = timeline;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.timeline + ")";
    }
}