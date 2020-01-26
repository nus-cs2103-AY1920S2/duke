public class Event extends Task {

    protected String at = null;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String fileString() {
        return "E | " + this.getStatusIcon() + " | " + description + " | " + at;
    }
}