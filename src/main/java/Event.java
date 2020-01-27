public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String getDesc() {
        return super.description;
    }

    public String getDate() {
        return at;
    }

    public String getType() {
        return "[E]";
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}