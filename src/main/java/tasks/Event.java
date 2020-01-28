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

    @Override
    public String toSaveFormat() {
        char d = super.getIsDone() ? '1' : '0';
        return "E | " + d + " | " + super.getDescription() + " | " + this.startAt;
    }
}
