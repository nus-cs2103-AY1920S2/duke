public class Event extends Task {
    String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, String at, boolean done) {
        super(description, done);
        this.at = at;
    }

    @Override
    public String toString() {
        String checkbox = "[" + super.getStatusIcon() + "]";
        return "[E]" + checkbox + " " + super.toString() + " (at: " + at + ")";
    }
}