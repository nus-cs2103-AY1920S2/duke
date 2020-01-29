public class Event extends Task {
    private String timeframe;

    Event(String description, String timeframe) {
        super(description);
        this.timeframe = timeframe;
    }

    Event(String description, String timeframe, boolean isCompleted) {
        super(description, isCompleted);
        this.timeframe = timeframe;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.timeframe + ")";
    }
}
