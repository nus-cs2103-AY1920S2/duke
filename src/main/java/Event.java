public class Event extends Task {

    protected String at;

    public Event(String desc, String at) {
        super(desc);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}