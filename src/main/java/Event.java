public class Event extends Task {
    protected String duration;

    public Event(String desc, String duration) {
        super(desc);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return String.format("[E] [%s] %s (at: %s)", this.getStatus(), this.description, this.duration);
    }
}
