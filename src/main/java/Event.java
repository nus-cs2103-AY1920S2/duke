public class Event extends Task {
    private String event;
    public Event(String task, String event) {
        super(task);
        this.event = event;
    }

    public String toString() {
        return String.format("[E]%s(at: %s)", super.toString(), this.event);
    }
}
