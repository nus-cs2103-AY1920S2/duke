package duchess.task;

public class Event extends Task {
    private String timeFrame;

    public Event(String description, String timeFrame) {
        super(description);
        this.timeFrame = timeFrame;
    }

    public Event(String description, String timeFrame, boolean isCompleted) {
        super(description, isCompleted);
        this.timeFrame = timeFrame;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.timeFrame + ")";
    }
}
