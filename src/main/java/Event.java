public class Event extends Task {
    protected String duration;

    public Event(String desc, String duration) {
        super(desc);
        this.duration = duration;
    }

    public Event(String desc, String duration, boolean isDone) {
        super(desc);
        super.isDone = isDone;
        this.duration = duration;

    }

    @Override
    public String generateSaveFileEntry() {
        return String.format("E | %d | %s | %s\n", this.getStatusAsInt(), this.description, this.duration);
    }

    @Override
    public String toString() {
        return String.format("[E] [%s] %s (at: %s)", this.getStatus(), this.description, this.duration);
    }
}
