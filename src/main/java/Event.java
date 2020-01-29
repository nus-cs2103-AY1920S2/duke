public class Event extends Task {
    protected String at;

    public Event(String desc, String at) {
        super(desc);
        this.at = at;
    }

    public String getAt() {
        return this.at;
    }

    @Override
    public String toStorageString() {
        return String.format("E | %s | %s | %s\n", super.getStatusInteger(), super.getDesc(),
                this.getAt());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
