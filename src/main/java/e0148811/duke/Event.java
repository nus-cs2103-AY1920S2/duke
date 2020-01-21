package e0148811.duke;

public class Event extends Task {
    public Event(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
