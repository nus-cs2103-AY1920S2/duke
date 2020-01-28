public class Event extends Task {
    protected String at;
    Event(String taskName, String at) {
        super(taskName);
        this.at = at;
    }

    String getAt() {
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + at + ")";
    }
}
