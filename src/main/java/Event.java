public class Event extends Task {
    private String event;
    public Event(String task, String event) {
        super(task);
        this.event = event;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.event);
    }

    @Override
    public String toFormatString() {
        return super.getStatus() ? String.format("E | 1 | %s | %s", super.getTask(), this.event)
                : String.format("E | 0 | %s | %s", super.getTask(), this.event);
    }
}
