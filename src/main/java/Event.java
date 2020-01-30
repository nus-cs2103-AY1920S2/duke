public class Event extends Task {

    protected String type;
    protected String at;

    public Event(boolean isDone, String description, String at) {
        super(isDone, description);
        this.type = "E";
        this.at = at;
    }

    @Override
    public String[] toDataTokens() {
        String isDoneString = String.valueOf(super.isDone);
        return new String[] {this.type, isDoneString, super.description, this.at};
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}