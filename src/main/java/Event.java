public class Event extends Task {
    protected String by;

    public Event(String desc, String by) {
        super(desc);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + by + ")";
    }

}
