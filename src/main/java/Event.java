public class Event extends Task {
    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String toString() {
        return "[E]" + super.toString() + "(at: " + at + ")";
    }
}