public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = (at.split(" ", 2))[1];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}