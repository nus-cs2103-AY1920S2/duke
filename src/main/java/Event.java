public class Event extends Task{
    private String atDate = "";

    public Event(String description, String atDate) {
        super(description, false);
        this.atDate = atDate;
    }

    public Event(String description, boolean isDone, String atDate) {
        super(description, isDone);
        this.atDate = atDate;
    }

    @Override
    public String getSaveRepresentation() {
        return "E|||" + getIsDone() + "|||" + getDescription() + "|||" + atDate + "\n";
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (at: %s)", "E", (getIsDone() ? "\u2713" : "\u2718"), getDescription(), atDate);
    }
}