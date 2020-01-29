package e0148811.duke;

public class Event extends Task {
    private String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public Event(boolean isDone, String description, String time) {
        super(isDone, description);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }

    @Override
    public String toSimplerString() {
        return "E//" + super.toSimplerString() + "//" + time;
    }
}
