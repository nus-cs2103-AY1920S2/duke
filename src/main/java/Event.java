public class Event extends Task{
    private String atDate = "";

    public Event(String description, String atDate) {
        super(description);
        this.atDate = atDate;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (at: %s)", "E", (getIsDone() ? "\u2713" : "\u2718"), getDescription(), atDate);
    }
}