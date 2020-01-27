public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (by: %s)", super.toString(), this.at);
    }

    @Override
    protected String getFileFormattedLine() {
        return String.format("E|%s|%s|%s", super.isDone ? "1" : "0", this.description, this.at);
    }
}
