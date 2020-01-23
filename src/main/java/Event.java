public class Event extends Task {
    protected String eventTime;

    Event(String unparsed) {
        String[] split = unparsed.split("/at");
        this.description = split[0].trim();
        this.eventTime = split[1].trim();
    }

    public String toString() {
        return "[D]" + super.toString() + " (at: " + this.eventTime + ")";
    }
}
