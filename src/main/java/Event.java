public class Event extends Task {
    private String at_schedule;

    public Event(String description, String at_schedule) {
        super(description);
        this.at_schedule = at_schedule;
    }

    @Override
    public String toString() {
        return String.format("[E] [%s] %s (at: %s)", getStatusIcon(), this.description, this.at_schedule);
    }
}
