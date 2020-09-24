package duke.task;

public class Event extends Task {
    public String timeFrame;
    public Event(String description, String timeFrame, boolean isDone) {
        super(description, isDone);
        this.timeFrame = timeFrame;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + timeFrame + ")";
    }
}
