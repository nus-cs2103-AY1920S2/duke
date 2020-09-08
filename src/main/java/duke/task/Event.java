package duke.task;

public class Event extends Task {
    String timeFrame;
    public Event(String description, String timeFrame) {
        super(description);
        this.timeFrame = timeFrame;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + timeFrame + ")";
    }
}
