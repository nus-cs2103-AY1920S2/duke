package tasks;

public class Event extends Task {
    private String startAt;

    public Event(String description, String startAt) {
        super(description);
        this.startAt = startAt;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.startAt + ")";
    }
}
