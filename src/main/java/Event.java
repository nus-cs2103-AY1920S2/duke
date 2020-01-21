public class Event extends Task {
    private String eventTime;
    
    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", getStatusIcon(), description, eventTime);
    }
}

