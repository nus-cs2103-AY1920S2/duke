public class Event extends Task {
    protected String timefin;

    public Event(String description, String timefin) {
        super(description);
        this.timefin = timefin;
    }
    @Override
    public String toString() {
        return "[E]" + "[" + super.getStatusIcon() + "] " + super.toString() + " (at: " + timefin + ")";
    }

}
