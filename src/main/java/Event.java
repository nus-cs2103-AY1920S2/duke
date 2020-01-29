public class Event extends Task {
    protected String eventTime;

    Event(String unparsed) {
        String[] split = unparsed.split("/at");
        this.description = split[0].trim();
        this.eventTime = split[1].trim();
        super.TYPE = TaskType.EVENT;
    }

    Event(String status, String description, String eventTime) {
        super(status, description);
        super.TYPE = TaskType.EVENT;
        this.eventTime = eventTime;
    }

    public String getTaskTime() {
        return this.eventTime;
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.eventTime + ")";
    }
}
