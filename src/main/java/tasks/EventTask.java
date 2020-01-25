package tasks;

public class EventTask extends Task {
    private String eventTime;

    public EventTask(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    public String getEventTime() {
        return eventTime;
    }

    @Override
    public String toString() {
        return String.format("%s%s (at: %s)", "[E]", super.toString(), eventTime);
    }
}
