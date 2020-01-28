public class Event extends Task {
    String timeRange;

    public Event(String description, String timeRange) {
        super(description);

        this.timeRange = timeRange;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + timeRange.replace("at", "at:") + ")";
    }

    @Override
    public String encode() {
        return "E | " +
                (isDone ? 1 : 0) +
                " | " + description +
                " | " + timeRange;
    }
}
