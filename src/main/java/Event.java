public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description.strip());
        this.at = at.strip();
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), at);
    }
}